/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.dsyz.common.core.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ower.dsyz.common.core.constant.ErrorCodeConstants;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2017年12月28日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse<T> {

    private CustomResponseHead head;

    private T                  body;


    public static <T> CustomResponse<T> success(T data) {
        CustomResponseHead head = new CustomResponseHead(true);
        return new CustomResponse<T>(data, head);
    }

    public static <T> CustomResponse<T> success(String msg, T data) {
        CustomResponseHead head = new CustomResponseHead(true);
        head.setErrorMsg(msg);
        return new CustomResponse<T>(data, head);
    }

    public static <T> CustomResponse<T> success(String msg) {
        CustomResponseHead head = new CustomResponseHead(true);
        head.setErrorMsg(msg);
        return new CustomResponse<T>(null, head);
    }
    public static <T> CustomResponse<T> error(String msg) {
        CustomResponseHead head = new CustomResponseHead(false);
        head.setErrorMsg(msg);
        return new CustomResponse<T>(null, head);
    }

    public static <T> CustomResponse<T> error(String errorCode, String msg) {
        CustomResponseHead head = new CustomResponseHead(false);
        head.setErrorCode(errorCode);
        head.setErrorMsg(msg);
        return new CustomResponse<T>(null, head);
    }

    public CustomResponse() {}

    private CustomResponse(T body, CustomResponseHead head) {
        this.head = head;
        this.body = body;
    }

    public CustomResponseHead getHead() {
        return head;
    }

    public void setHead(CustomResponseHead head) {
        this.head = head;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
    @JsonIgnore
    public Boolean isSuccess(){
    	return ErrorCodeConstants.SUCCESS.equals(this.head.getErrorCode());
    }
    
    @Override
    public String toString() {
        return "CustomResponse [head=" + head + ", body=" + body + "]";
    }

}
