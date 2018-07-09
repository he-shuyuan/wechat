/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.dsyz.common.core.model;

/**
 * <pre>
 * 结果集
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月11日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class ResultMsg {

    /**
     * 默认成功编码
     */
    private final static String DEFAULT_SUCCESS_CODE = "0";
    
    /**
     * 默认失败编码
     */
    private final static String DEFAULT_FAILURE_CODE = "1";
    /**
     * 消息
     */
    private String  msg;

    /**
     * 是否成功
     */
    private boolean result;

    /**
     * 拓展对象
     */
    private Object   ob;

    /**
     * 错误码
     */
    private String code;
    
    
    private ResultMsg() {

    }

    public static  ResultMsg success(String msg) {
        return new ResultMsg().Msg(msg).Result(true).Code(DEFAULT_SUCCESS_CODE);
    }

    public static  ResultMsg success(Object ob) {
        return new ResultMsg().Result(true).Code(DEFAULT_SUCCESS_CODE).Ob(ob);
    }
    
    public static ResultMsg success() {
        return new ResultMsg().Result(true).Code(DEFAULT_SUCCESS_CODE);
    }

    public static  ResultMsg success(String msg, Object t) {
        return new ResultMsg().Msg(msg).Result(true).Ob(t).Code(DEFAULT_SUCCESS_CODE);
    }

    public static ResultMsg error(String msg) {
        return new ResultMsg().Msg(msg).Result(false).Code(DEFAULT_FAILURE_CODE);
    }

    public static  ResultMsg error(String code,String msg) {
        return new ResultMsg().Result(false).Code(code);
    }
    
    public static  ResultMsg error() {
        return new ResultMsg().Result(false).Code(DEFAULT_FAILURE_CODE);
    }

    public String getMsg() {
        return msg;
    }



    public void setMsg(String msg) {
        this.msg = msg;
    }



    private ResultMsg Msg(String msg) {
        this.msg = msg;
        return this;
    }

    private ResultMsg Result(boolean result) {
        this.result = result;
        return this;
    }
    
    private ResultMsg Ob(Object ob) {
        this.ob = ob;
        return this;
    }

    private ResultMsg Code(String code){
        this.code = code;
        return this;
    }
    
    public boolean isResult() {
        return result;
    }

    
    public Object getOb() {
        return ob;
    }

    
    public void setResult(boolean result) {
        this.result = result;
    }

    
    public void setOb(Object ob) {
        this.ob = ob;
    }

    
    public String getCode() {
        return code;
    }

    
    public void setCode(String code) {
        this.code = code;
    }

 

}
