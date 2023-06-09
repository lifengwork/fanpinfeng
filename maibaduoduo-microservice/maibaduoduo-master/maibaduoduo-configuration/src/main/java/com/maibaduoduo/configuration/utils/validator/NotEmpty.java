package com.maibaduoduo.configuration.utils.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {AutoNotEmptyValidator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmpty {
    String message() default "{数据不能为空。。}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
