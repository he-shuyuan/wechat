/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.core.rest.impl;

import com.ower.dsyz.common.core.response.CustomResponse;

/**
 * <pre>
 * 内部请求接口
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月23日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public interface ICustomRestClient {

   <T> CustomResponse<T> postInner(String url,String data,String contentType,Class<T> responseClass);
   
   <T> CustomResponse<T> postInner(String url,Object data,String contentType,Class<T> responseClass);
   
   <T> CustomResponse<T> postInner(String url,Object data,Class<T> responseClass);
   
   <T> CustomResponse<T> postInner(String url,String data,Class<T> responseClass);
   
}
