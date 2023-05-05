/*
 * *
 *  * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *  *
 *  * SAAS系统设计研发交流
 *  *
 *  * https://www.maibaduoduo.com
 *
 */

package org.dromara.myth.springcloud.feign;

import feign.Feign;
import feign.InvocationHandlerFactory;
import feign.InvocationHandlerFactory.MethodHandler;
import feign.Target;
import feign.Util;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import org.dromara.myth.annotation.Myth;
import org.dromara.myth.common.bean.context.MythTransactionContext;
import org.dromara.myth.common.bean.entity.MythInvocation;
import org.dromara.myth.common.bean.entity.MythParticipant;
import org.dromara.myth.core.concurrent.threadlocal.TransactionContextLocal;
import org.dromara.myth.core.helper.SpringBeanUtils;
import org.dromara.myth.core.service.engine.MythTransactionEngine;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * MythFeignCircuitBreakerInvocationHandler
 * @Auth saas
 */
public class MythFeignCircuitBreakerInvocationHandler implements InvocationHandler {
    private final CircuitBreakerFactory factory;
    private final Target<?> target;
    private final Map<Method, InvocationHandlerFactory.MethodHandler> dispatch;
    private final FallbackFactory<?> nullableFallbackFactory;
    private final Map<Method, Method> fallbackMethodMap;

    public MythFeignCircuitBreakerInvocationHandler(CircuitBreakerFactory factory, Target<?> target, Map<Method, InvocationHandlerFactory.MethodHandler> dispatch, FallbackFactory<?> nullableFallbackFactory) {
        this.factory = factory;
        this.target = (Target) Util.checkNotNull(target, "target", new Object[0]);
        this.dispatch = (Map) Util.checkNotNull(dispatch, "dispatch", new Object[0]);
        this.fallbackMethodMap = toFallbackMethod(dispatch);
        this.nullableFallbackFactory = nullableFallbackFactory;
    }

    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        if (!"equals".equals(method.getName())) {
            if ("hashCode".equals(method.getName())) {
                return this.hashCode();
            } else if ("toString".equals(method.getName())) {
                return this.toString();
            } else if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            } else {
                final Myth myth = method.getAnnotation(Myth.class);
                if(Objects.nonNull(myth)){
                    try {
                        final MythTransactionEngine mythTransactionEngine =
                                SpringBeanUtils.getInstance().getBean(MythTransactionEngine.class);
                        final MythParticipant participant = buildParticipant(myth, method, args);
                        if (Objects.nonNull(participant)) {
                            mythTransactionEngine.registerParticipant(participant);
                        }
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
                String circuitName = Feign.configKey(this.target.type(), method);
                CircuitBreaker circuitBreaker = this.factory.create(circuitName);
                Supplier<Object> supplier = this.asSupplier(method, args);
                if (this.nullableFallbackFactory != null) {
                    Function<Throwable, Object> fallbackFunction = (throwable) -> {
                        Object fallback = this.nullableFallbackFactory.create(throwable);
                        try {
                            return ((Method) this.fallbackMethodMap.get(method)).invoke(fallback, args);
                        } catch (Exception var6) {
                            throw new IllegalStateException(var6);
                        }
                    };
                    return circuitBreaker.run(supplier, fallbackFunction);
                } else {
                    return circuitBreaker.run(supplier);
                }
            }
        } else {
            try {
                Object otherHandler = args.length > 0 && args[0] != null ? Proxy.getInvocationHandler(args[0]) : null;
                return this.equals(otherHandler);
            } catch (IllegalArgumentException var8) {
                return false;
            }
        }
    }

    private Supplier<Object> asSupplier(final Method method, final Object[] args) {
        return () -> {
            try {
                return ((MethodHandler) this.dispatch.get(method)).invoke(args);
            } catch (RuntimeException var4) {
                throw var4;
            } catch (Throwable var5) {
                throw new RuntimeException(var5);
            }
        };
    }

    static Map<Method, Method> toFallbackMethod(Map<Method, InvocationHandlerFactory.MethodHandler> dispatch) {
        Map<Method, Method> result = new LinkedHashMap();
        Iterator var2 = dispatch.keySet().iterator();

        while (var2.hasNext()) {
            Method method = (Method) var2.next();
            method.setAccessible(true);
            result.put(method, method);
        }

        return result;
    }

    public boolean equals(Object obj) {
        if (obj instanceof MythFeignCircuitBreakerInvocationHandler) {
            MythFeignCircuitBreakerInvocationHandler other = (MythFeignCircuitBreakerInvocationHandler) obj;
            return this.target.equals(other.target);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.target.hashCode();
    }

    public String toString() {
        return this.target.toString();
    }

    private MythParticipant buildParticipant(final Myth myth, final Method method, final Object[] args) {
        final MythTransactionContext mythTransactionContext = TransactionContextLocal.getInstance().get();

        MythParticipant participant;
        if (Objects.nonNull(mythTransactionContext)) {
            final Class declaringClass = myth.target();
            MythInvocation mythInvocation =
                    new MythInvocation(declaringClass, method.getName(), method.getParameterTypes(), args);
            final Integer pattern = myth.pattern().getCode();
            //封装调用点
            participant = new MythParticipant(mythTransactionContext.getTransId(),
                    myth.destination(),
                    pattern,
                    mythInvocation);
            return participant;
        }
        return null;
    }
}