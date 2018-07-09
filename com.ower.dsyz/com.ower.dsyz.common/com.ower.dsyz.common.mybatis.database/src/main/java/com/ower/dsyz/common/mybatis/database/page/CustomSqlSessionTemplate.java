/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.mybatis.database.page;

import java.util.List;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.util.Assert;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ower.dsyz.common.core.page.PageQueryResult;
import com.ower.dsyz.common.core.page.PageRequestParam;

/**
 * <pre>
 * 重写selectOne方法
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月4日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class CustomSqlSessionTemplate
extends SqlSessionTemplate
{
private final SqlSession superSqlSessionProxy;

public CustomSqlSessionTemplate(SqlSessionFactory sqlSessionFactory)
{
  this(sqlSessionFactory, sqlSessionFactory.getConfiguration().getDefaultExecutorType());
}

public CustomSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType)
{
  this(sqlSessionFactory, executorType, new MyBatisExceptionTranslator(sqlSessionFactory
    .getConfiguration().getEnvironment().getDataSource(), true));
}

public CustomSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType, PersistenceExceptionTranslator exceptionTranslator)
{
  super(sqlSessionFactory, executorType, exceptionTranslator);
  
  Assert.notNull(sqlSessionFactory, "Property 'sqlSessionFactory' is required");
  Assert.notNull(executorType, "Property 'executorType' is required");
  
  MetaObject metaQqlSessionFactory = sqlSessionFactory.getConfiguration().newMetaObject(this);
  this.superSqlSessionProxy = ((SqlSession)metaQqlSessionFactory.getValue("sqlSessionProxy"));
}

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> T selectOne(String statement, Object parameter)
{
  if ((parameter instanceof PageRequestParam))
  {
	  PageRequestParam pageRequestParam = (PageRequestParam)parameter;
    PageHelper.startPage(pageRequestParam.getPageIndex(), pageRequestParam.getPageSize());
    
    Object result = this.superSqlSessionProxy.selectList(statement, parameter);
    if ((result instanceof Page))
    {
      Page<Object> page = (Page)result;
      List<Object> data = page.getResult();
      PageQueryResult<Object> queryResult = new PageQueryResult<Object>(page, data);
      return (T) queryResult;
    }
    return (T) result;
  }
  return this.superSqlSessionProxy.selectOne(statement, parameter);
}
}

