package com.maibaduoduo.common.utils.validator;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 数据校验
 */
public class AutoNotEmptyValidator implements ConstraintValidator<NotEmpty, String> {
    public void initialize(NotEmpty constraintAnnotation){
        //do nothing
    }
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(StringUtils.isEmpty(value)){
            return false;
        }
        return true;
    }
}