/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.core.rest.impl;

import java.util.Map;

import com.ower.dsyz.common.core.response.CustomResponse;

/**
 * <pre>
 * 内部请求接口
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月23日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public interface ICustomRestClient {

	/**
	 * 
	 * @param url
	 * @param data
	 * @param contentType
	 * @param responseClass
	 * @return
	 */
   <T> CustomResponse<T> postInner(String url,Object data,String contentType,Class<T> responseClass);
   /**
    * 
    * @param url
    * @param data
    * @param responseClass
    * @return
    */
   <T> CustomResponse<T> postInner(String url,Object data,Class<T> responseClass);
  
   /**
    * 
    * @param url
    * @param data
    * @param responseClass 
    * @param extHeader 头部拓展
    * @return
    */
   <T> CustomResponse<T> postInner(String url,Object data,Class<T> responseClass,Map<String,String> extHeader);
   
  /**
   * 
   * @param url
   * @param data
   * @param contentType
   * @param responseClass
   * @param extHeader
   * @return
   */
  <T> CustomResponse<T> postInner(String url,Object data,String contentType,Class<T> responseClass,Map<String,String> extHeader);
}
