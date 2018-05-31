/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.hsy.common.core.request;

import java.util.HashMap;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *  通用入参
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

public class CustomRequestParam extends HashMap<String,Object>{

    private Logger logger=LoggerFactory.getLogger(CustomRequestParam.class);
    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 1L;
    
    public CustomRequestParam(){
       super();
    }
    
    public String getString(String key){
        Object ob = this.get(key);
        if(ob == null){
            return null;
        }
        if(ob instanceof String){
            return (String)ob;
        }
        logger.debug("{}不是String类型，转换失败",ob);
        return String.valueOf(ob);
    }
    
    public Integer getInt(String key){
        Object ob = this.get(key);
        if(ob == null){
            return 0;
        }
        if(ob instanceof Integer){
            return (Integer)ob;
        }
        logger.debug("{}不是Integer类型，转换失败",ob);
        return NumberUtils.toInt(String.valueOf(ob), 0);
    }
    
}
