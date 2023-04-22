
package com.maibaduoduo.database.datasource.dynamic.annotation;

import java.lang.annotation.*;

/**
 * 多数据源注解
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DS {
    String value() default "default";
}
