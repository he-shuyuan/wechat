/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.core.page;

import com.ower.dsyz.common.core.request.CustomRequestParam;

/**
 * <pre>
 * 接收分页参数
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

public class PageRequestParam extends CustomRequestParam{

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = -7364605008252414290L;

    private int pageIndex = 1;
    
    private int pageSize = 10;

    public PageRequestParam(){
    	super();
    	super.put("pageIndex", 1);
    	super.put("pageSize", 10);
    }
    
    public int getPageSize() {
        return pageSize;
    }

    

    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }




	public int getPageIndex() {
		return pageIndex;
	}




	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

    @Override
    public Object put(String key, Object value) {
        if (key.equals("pageIndex")) {
            this.setPageIndex(Integer.parseInt(String.valueOf(value)));
        } else if (key.equals("pageSize")) {
            this.setPageSize(Integer.parseInt(String.valueOf(value)));
        } 
          return super.put(key, value);
       
    }
	
    
    
}
