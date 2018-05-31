/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.dsyz.common.core.page;

import java.util.List;
import com.github.pagehelper.Page;

/**
 * <pre>
 *   分页结果参数
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月4日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class PageQueryResult<T> {

    private PageInfo pageInfo;

    private List<T>  data;


    public PageQueryResult() {

    }

    public PageQueryResult(Page<T> page, List<T> data) {
        this.pageInfo = PageInfo.getInstance(page);
        this.data = data;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public List<T> getData() {
        return data;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
