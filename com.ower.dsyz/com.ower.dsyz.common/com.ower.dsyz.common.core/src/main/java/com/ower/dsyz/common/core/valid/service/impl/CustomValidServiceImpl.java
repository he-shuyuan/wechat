/**
 * Copyright(c) Foresee Science & Technology Ltd.
 */
package com.ower.dsyz.common.core.valid.service.impl;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.stereotype.Service;

import com.ower.dsyz.common.core.constant.ErrorCodeConstants;
import com.ower.dsyz.common.core.exception.CustomRunTimeException;
import com.ower.dsyz.common.core.valid.service.ICustomValidService;

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
    public <T> void validator(T t) {

        Set<ConstraintViolation<T>> violations = validator.validate(t);
        int errSize = violations.size();
        if (errSize > 0) {
            for (ConstraintViolation<T> violation : violations) {
               throw new CustomRunTimeException(ErrorCodeConstants.PARAM_ERROR, violation.getMessage());
            }
        }
      

    }

}
