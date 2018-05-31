/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.hsy.common.core.valid.service;

import com.ower.hsy.common.core.model.ResultMsg;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年3月22日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public interface ICustomValidService {

    /**
     * 参数校验
     * @param t
     * @return ResultMsg
     */
    <T> ResultMsg validator(T t);
}
