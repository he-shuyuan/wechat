/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.hsy.common.core.valid.service.impl;

import java.util.Arrays;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.stereotype.Service;
import com.ower.hsy.common.core.model.ResultMsg;
import com.ower.hsy.common.core.valid.service.ICustomValidService;

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
@Service
public class CustomValidServiceImpl implements ICustomValidService {

    private Validator validator;


    public CustomValidServiceImpl() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public <T> ResultMsg validator(T t) {

        Set<ConstraintViolation<T>> violations = validator.validate(t);
        int errSize = violations.size();

        String[] errMsg = new String[errSize];
        boolean result = true;
        if (errSize > 0) {
            int i = 0;
            for (ConstraintViolation<T> violation : violations) {
                errMsg[i] = violation.getMessage();
                i++;
            }
            result = false;
        }
        if (result) {
            return ResultMsg.success();
        } else {
           String errorM =  Arrays.toString(errMsg);
           errorM = errorM.substring(1,errorM.length()-1);
            return ResultMsg.error(errorM);
        }

    }

}
