package com.maibaduoduo.base.other.queue.exception;

/**
 * 线程池捕获线程异常；
 * 默认如果线程出现异常直接有jvm捕获，trycach是捕获不到的。
 */
public class ExceptionRunnerTest implements Runnable {
    @Override
    public void run() {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandle());
        System.out.println(1 / 0);
    }
}
