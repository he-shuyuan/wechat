/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.hsy.common.core.listener.bean;

import com.ower.hsy.common.core.util.IDUtil;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月17日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public abstract class CustomEventSource implements ICustomEventSource {

    private String sourceId;
    
    public CustomEventSource(){
        this.sourceId = IDUtil.getUUID();
    }
    @Override
    public String getSourceId() {

        return this.sourceId;
    }

}
