/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.core.listener.bean;

import java.util.EventObject;
import com.ower.dsyz.common.core.util.IDUtil;

/**
 * <pre>
 * 事件
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

public final class CustomEvent extends EventObject{
    
    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 1L;

    private ICustomEventSource source;
    
    private String sourceName;
    
    private String eventId;

    private String key;
    
    public CustomEvent(ICustomEventSource source, String sourceName) {
        super(source);
        this.source = source;
        this.sourceName = sourceName;
        this.eventId = IDUtil.getUUID();
    }
    
    public CustomEvent(ICustomEventSource source, String sourceName,String eventId) {
        super(source);
        this.source = source;
        this.sourceName = sourceName;
        this.eventId = eventId;
    }

    
    public ICustomEventSource getSource() {
        return source;
    }


    
    public String getSourceName() {
        return sourceName;
    }


    
    public void setSource(ICustomEventSource source) {
        this.source = source;
    }


    
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    
    public String getEventId() {
        return eventId;
    }

    
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    
    public String getKey() {
        return key;
    }

    
    public void setKey(String key) {
        this.key = key;
    }

}
