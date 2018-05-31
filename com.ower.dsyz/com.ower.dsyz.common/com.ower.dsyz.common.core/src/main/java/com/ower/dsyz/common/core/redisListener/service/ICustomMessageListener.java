/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.core.redisListener.service;

/**
 * <pre>
 *
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月31日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public interface ICustomMessageListener {

    /**
     * 触发回调
     * @param channel
     * @param message void
     */
    public abstract void onMessage(String channel, String message);
}
