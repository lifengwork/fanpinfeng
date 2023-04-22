package com.maibaduoduo.web.config;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2019/10/28 0028.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestDemo {

    /**
     *
     * @return
     */
    String value() default "user";

}
