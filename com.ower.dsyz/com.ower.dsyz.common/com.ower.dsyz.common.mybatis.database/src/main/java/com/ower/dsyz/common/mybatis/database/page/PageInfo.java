/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.mybatis.database.page;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.pagehelper.Page;

/**
 * <pre>
 * 分页信息
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

public class PageInfo implements Serializable
{
   

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = -3548281756019103885L;

    private static final Logger logger = LoggerFactory.getLogger(PageInfo.class);

    protected int pageIndex = 1;
    protected int pageSize = 10;
    protected int pageCount = 0;
    protected int recordCount = 0;
    protected int sizeOfCurrentPage = 0;
    private int indexOfFirstItemOfCurrentPage = 0;
    private int prePageIndex = 0;
    private int nextPageIndex = 0;
    private boolean existsPrePage = false;
    private boolean existsNextPage = false;
    
    public PageInfo()
    {
      this.pageIndex = 1;
      this.pageSize = 10;
    }
    
    private PageInfo(Page<?> page)
    {
      this.pageIndex = page.getPageNum();
      this.pageSize = page.getPageSize();
      this.recordCount = (int) page.getTotal();
      

      calculate();
    }
    
    public static PageInfo getInstance(Page<?> page)
    {
      return new PageInfo(page);
    }
    
    public boolean existsNextPage()
    {
      return this.existsNextPage;
    }
    
    public boolean existsPrePage()
    {
      return this.existsPrePage;
    }
    
    public int getIndexOfFirstItemOfCurrentPage()
    {
      return this.indexOfFirstItemOfCurrentPage;
    }
    
    public int getNextPageIndex()
    {
      return this.nextPageIndex;
    }
    
    public int getPrePageIndex()
    {
      return this.prePageIndex;
    }
    
    public int getPageCount()
    {
      return this.pageCount;
    }
    
    public int getRecordCount()
    {
      return this.recordCount;
    }
    
    public int getSizeOfCurrentPage()
    {
      return this.sizeOfCurrentPage;
    }
    
    public void setSizeOfCurrentPage(int sizeOfCurrentPage)
    {
      this.sizeOfCurrentPage = sizeOfCurrentPage;
    }
    
    public int getPageSize()
    {
      return this.pageSize;
    }
    
    public int getPageIndex()
    {
      return this.pageIndex;
    }
    
    private void calculate()
    {
      logger.debug("计算分页信息...");
      if (this.pageIndex <= 0)
      {
        if (logger.isDebugEnabled()) {
          logger.warn("pageIndex属性的取值为" + this.pageIndex + ", 现将其取值修正为1");
        }
        this.pageIndex = 1;
      }
      if (this.recordCount < 0)
      {
        if (logger.isDebugEnabled()) {
          logger.warn("recordCount属性的取值为" + this.recordCount + ", 现将其取值修正为0");
        }
        this.recordCount = 0;
      }
      if (this.pageSize <= 0)
      {
        if (logger.isDebugEnabled()) {
          logger.warn("pageSize属性的取值为" + this.pageSize + ", 现将其取值修正为默认值" + 10);
        }
        this.pageSize = 10;
      }
      if (this.recordCount % this.pageSize == 0) {
        this.pageCount = (this.recordCount / this.pageSize);
      } else {
        this.pageCount = (this.recordCount / this.pageSize + 1);
      }
      if (this.pageCount <= 0) {
        this.pageIndex = 0;
      } else if (this.pageCount < this.pageIndex) {
        this.pageIndex = this.pageCount;
      }
      if (this.pageIndex <= 1)
      {
        this.prePageIndex = 1;
        this.existsPrePage = false;
      }
      else
      {
        this.prePageIndex = (this.pageIndex - 1);
        this.existsPrePage = true;
      }
      if (this.pageIndex <= 1)
      {
        if (this.pageIndex < 1)
        {
          this.nextPageIndex = 1;
          this.existsNextPage = false;
        }
        else if (this.pageCount >= 2)
        {
          this.nextPageIndex = 2;
          this.existsNextPage = true;
        }
        else
        {
          this.nextPageIndex = 1;
          this.existsNextPage = false;
        }
      }
      else if (this.pageIndex >= this.pageCount)
      {
        this.nextPageIndex = this.pageCount;
        this.existsNextPage = false;
      }
      else
      {
        this.nextPageIndex = (this.pageIndex + 1);
        this.existsNextPage = true;
      }
      if (this.pageIndex <= 0) {
        this.indexOfFirstItemOfCurrentPage = 0;
      } else {
        this.indexOfFirstItemOfCurrentPage = ((this.pageIndex - 1) * this.pageSize);
      }
      if (logger.isDebugEnabled()) {
        logger.debug("分页信息计算结果【目标页面页码：" + this.pageIndex + "前一页面页码：" + this.prePageIndex + "下一页面页码：" + this.nextPageIndex + "总页数：" + this.pageCount + "总记录数：" + this.recordCount + "是否存在前一页：" + this.existsPrePage + "是否存在下一页：" + this.existsNextPage + "】");
      }
    }
  }

