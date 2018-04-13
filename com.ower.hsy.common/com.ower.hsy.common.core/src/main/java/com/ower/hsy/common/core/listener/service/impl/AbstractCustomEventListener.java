/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.hsy.common.core.listener.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ower.hsy.common.core.listener.bean.CustomEvent;
import com.ower.hsy.common.core.listener.bean.ICustomEventSource;
import com.ower.hsy.common.core.listener.service.ICustomEventListener;

/**
 * <pre>
 * 默认监听器
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

public abstract class AbstractCustomEventListener implements ICustomEventListener {

    private Logger                            logger    = LoggerFactory.getLogger(AbstractCustomEventListener.class);

    @Override
    public void onCustomEventChange(CustomEvent event) {
        logger.info("\n【监听器】(key=[{}],id=[{}])的监听器接收事件",event.getKey(),event.getEventId());
        ICustomEventSource t = (ICustomEventSource) event.getSource();
        this.handlerEvent(t);
        
    }

    protected abstract void handlerEvent(ICustomEventSource t);
}
