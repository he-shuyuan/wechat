package com.ower.dsyz.wechat.constant;
/**
 * <pre>
 * redis缓存时间
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2017年5月11日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class RedisExpires {

    /**
     * 一天
     */
    public static final Long DAY=60*60*24L;
    
    /**
     * 一个小时
     */
    public static final Long HOUR=60*60L;
    
    /**
     * 半天
     */
    public static final Long HALF_DAY=60*60*12L;
    
    /**
     * 半小时
     */
    public static final Long HALF_HOUR=60*30L;
    
    /**
     * 一个星期
     */
    public static final Long WEEK=60*60*24*7L;
    
   /**
    * 一个月
    */
    public static final Long MONTH=60*60*24*30L;
    
    /**
     * 半个月
     */
    public static final Long HALF_MONTH=60*60*24*15L;
    
    /**
     * 一分钟
     */
    public  static final Long MINUTE=60L;
    
    /**
     * 半分钟
     */
    public  static final Long HALF_MINUTE=30L;
}

