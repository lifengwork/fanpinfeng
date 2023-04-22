package com.maibaduoduo.base.other.queue.CLH;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
public class Test {
    private volatile Integer index = 100;
    public static void main(String[] args) {
        Integer index1 = 4;
        Integer index = 0;
        Integer index3 = ++index+index1;
        System.out.println(index3);
    }
}
