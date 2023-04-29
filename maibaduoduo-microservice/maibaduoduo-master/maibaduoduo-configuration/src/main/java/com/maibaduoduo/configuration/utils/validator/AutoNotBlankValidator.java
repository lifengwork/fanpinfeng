package com.maibaduoduo.configuration.utils.validator;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 数据校验
 */
public class AutoNotBlankValidator implements ConstraintValidator<NotEmpty, String> {
    public void initialize(NotEmpty constraintAnnotation){
        //do nothing
    }
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(StringUtils.isBlank(value)){
            return false;
        }
        return true;
    }
}