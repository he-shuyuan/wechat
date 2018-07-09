/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.core.listener.service;

import java.util.EventListener;
import com.ower.dsyz.common.core.listener.bean.CustomEvent;

/**
 * <pre>
 * 事件监听接口
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

public interface ICustomEventListener extends EventListener{

    /**
     * 消息处理
     * @param event void
     */
    void onCustomEventChange(CustomEvent event);
}
