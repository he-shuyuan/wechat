/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.util;

import java.util.Map;

/**
 * <pre>
 * 线程工具类
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月11日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class ThreadLocalUtil {

    /**
     * 存放微信后台请求的所有参数
     */
    public static ThreadLocal<Map<String,String>> wxParams = new ThreadLocal<Map<String,String>>();
    
    /**
     * 清空所有的线程本地变量
     */
    public static void clean(){
        wxParams.remove();
    }
}

