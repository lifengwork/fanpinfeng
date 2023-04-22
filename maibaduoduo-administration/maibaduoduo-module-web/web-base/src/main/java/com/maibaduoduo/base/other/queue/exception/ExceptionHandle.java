package com.maibaduoduo.base.other.queue.exception;

public class ExceptionHandle implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("线程异常捕获"+t.getId()+" "+e.getStackTrace());
    }
}
