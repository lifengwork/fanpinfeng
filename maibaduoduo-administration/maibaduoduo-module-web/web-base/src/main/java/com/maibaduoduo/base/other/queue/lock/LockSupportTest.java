package com.maibaduoduo.base.other.queue.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
public class LockSupportTest {
    public static void main(String[] args) {
        //LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(20));
        LockSupport.parkNanos(new Object(),TimeUnit.SECONDS.toNanos(20));//添加阻塞对象
    }
}
