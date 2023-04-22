package com.maibaduoduo.base.other;

import java.util.concurrent.atomic.AtomicInteger;

public class OperateDemo {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
    public static void main(String[] args) {
//        Integer num = 100;
//        println(num);
//        println(num<<1);
//        println(num>>1);
//        println(50>>>10);
        println(RUNNING);
        println(SHUTDOWN);
        println(STOP);
        println(TIDYING);
        println(TERMINATED);
        println(-1);
        println(1);
    }
    private static void println(Integer obj){
        System.out.println(Integer.toBinaryString(obj));
    }
}
