/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.core.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.UUID;

/**
 * <pre>
 * 数据库主键生成器
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月16日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public abstract class IDUtil {

    
    
    public static String getUUID(){
       return UUID.randomUUID().toString().replace("-", "");
    }
    
    
    public static String getCurrentTimeID(){
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return System.currentTimeMillis()+runtimeMXBean.getName().split("@")[0];  
    }
}
