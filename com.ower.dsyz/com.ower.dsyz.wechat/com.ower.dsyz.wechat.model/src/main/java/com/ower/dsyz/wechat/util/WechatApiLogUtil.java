/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ower.dsyz.common.core.model.ResultMsg;
import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月12日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class WechatApiLogUtil {

    private final static Logger log = LoggerFactory.getLogger(WechatApiLogUtil.class);
    /**
     * 包装微信请求,统一处理异常
     * 
     * @param execute
     */
    public static <T> ResultMsg execute(String apiDesc,IWxApiInvoke<T> wxApiInvoke) {
        try {
            log.info("{},请求微信接口开始",apiDesc);
            Long start = System.currentTimeMillis();
            T t = wxApiInvoke.invoke();
            long costTime = System.currentTimeMillis() - start;
            log.info("{},请求微信接口成功,耗时{}毫秒,返回{}",apiDesc,costTime,t);
            return ResultMsg.success(t);
        } catch (WxErrorException e) {
            log.error("{},请求微信接口失败:{}\n{}",e.getMessage(),apiDesc,e);
            return ResultMsg.error(e.getMessage());
        }
    }
    

   public interface IWxApiInvoke<T> {

    /**
    * 
    * 统一调用
    * @return
    * @throws WxErrorException T
    */
    T invoke() throws WxErrorException;

}
    
}
