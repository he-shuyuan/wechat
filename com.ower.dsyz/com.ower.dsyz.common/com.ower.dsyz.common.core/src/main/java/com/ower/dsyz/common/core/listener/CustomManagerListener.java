/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.dsyz.common.core.listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ower.dsyz.common.core.listener.bean.CustomEvent;
import com.ower.dsyz.common.core.listener.bean.CustomEventSource;
import com.ower.dsyz.common.core.listener.service.ICustomEventListener;
import com.ower.dsyz.common.core.listener.util.ListenerThreadPoolUtil;

/**
 * <pre>
 * 事件和管理，注册器
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月17日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
//@Component
public class CustomManagerListener {

    private Map<Class<? extends CustomEventSource>, ICustomEventListener> listeners = null;

    private Logger                            logger    = LoggerFactory.getLogger(CustomManagerListener.class);


    public CustomManagerListener() {
        this.listeners = new ConcurrentHashMap<>();
        logger.info("\n【监听器】注册器初始化成功=[{}]",this);
    }

    /**
     * 
     * 添加一个监听器
     * @param key
     * @param e void
     */
    public void addCustomEventListener(Class<? extends CustomEventSource> clazz, ICustomEventListener e) {
        this.listeners.put(clazz, e);
        logger.info("\n【监听器】key=[{}]注册成功",clazz);
    }

    /**
     * 删除一个监听器
     * @param key void
     */
    public void deleteCustomEventListener(Class<? extends CustomEventSource> clazz) {
        this.listeners.remove(clazz);
        logger.info("\n【监听器】key=[{}]删除成功",clazz);
    }

    /**
     * 
     * 相应操作
     * @param clazz
     * @param event void
     */
    public void fireCustomEventListener(CustomEvent event) {
        Class<?> clazz = event.getSource().getClass();
        ListenerThreadPoolUtil.poolExecutor.execute(new Runnable() { 
            @Override
            public void run() {
                ICustomEventListener currentListener = CustomManagerListener.this.listeners.get(clazz);
                if (currentListener == null) {
                    logger.warn("\n【监听器】不存在key=[{}]的监听器，请检查事件源",clazz);
                    return;
                }
                //event.setKey(clazz.getName());
                currentListener.onCustomEventChange(event);
                
            }
        });
    }
}
