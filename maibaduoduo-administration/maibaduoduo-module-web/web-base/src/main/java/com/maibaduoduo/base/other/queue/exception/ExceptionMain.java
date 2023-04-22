package com.maibaduoduo.base.other.queue.exception;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExceptionMain {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(1,2, 3,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));
        executorService.execute(new ExceptionRunnerTest());
        executorService.shutdown();
    }
}
