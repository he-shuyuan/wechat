/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.mybatis.generator.ext.plugins;

import java.util.List;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2017年12月27日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class MysqlPaginationPlugin
  extends PluginAdapter
{
  public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
  {
    addLimit(topLevelClass, introspectedTable, "limitStart");
    addLimit(topLevelClass, introspectedTable, "limitEnd");
    return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
  }
  
  public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
  {
    return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
  }
  
  private void addLimit(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name)
  {
    CommentGenerator commentGenerator = this.context.getCommentGenerator();
    Field field = new Field();
    field.setVisibility(JavaVisibility.PROTECTED);
    field.setType(FullyQualifiedJavaType.getIntInstance());
    field.setName(name);
    field.setInitializationString("-1");
    commentGenerator.addFieldComment(field, introspectedTable);
    topLevelClass.addField(field);
    char c = name.charAt(0);
    String camel = Character.toUpperCase(c) + name.substring(1);
    Method method = new Method();
    method.setVisibility(JavaVisibility.PUBLIC);
    method.setName("set" + camel);
    method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), name));
    method.addBodyLine("this." + name + "=" + name + ";");
    commentGenerator.addGeneralMethodComment(method, introspectedTable);
    topLevelClass.addMethod(method);
    method = new Method();
    method.setVisibility(JavaVisibility.PUBLIC);
    method.setReturnType(FullyQualifiedJavaType.getIntInstance());
    method.setName("get" + camel);
    method.addBodyLine("return " + name + ";");
    commentGenerator.addGeneralMethodComment(method, introspectedTable);
    topLevelClass.addMethod(method);
  }
  
  public boolean validate(List<String> warnings)
  {
    return true;
  }
  
  public static void generate()
  {
    String config = MysqlPaginationPlugin.class.getClassLoader().getResource("generatorConfig.xml").getFile();
    String[] arg = { "-configfile", config, "-overwrite" };
    ShellRunner.main(arg);
  }
  
  public static void main(String[] args) {}
}

