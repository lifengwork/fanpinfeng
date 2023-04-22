package com.maibaduoduo.web.config;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by Administrator on 2019/10/28 0028.
 */

/**
 * 依据注解把当前用户的信息绑定到加TestDemo注解的User上
 */
public class AutoMethodParamResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (methodParameter.hasParameterAnnotation(TestDemo.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        TestDemo currentUserAnnotation = methodParameter.getParameterAnnotation(TestDemo.class);
        return nativeWebRequest.getAttribute(currentUserAnnotation.value(), NativeWebRequest.SCOPE_REQUEST);//从session中获取值
    }
}
