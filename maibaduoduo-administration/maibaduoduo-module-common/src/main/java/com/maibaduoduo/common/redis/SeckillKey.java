package com.maibaduoduo.common.redis;

/**
 * 秒啥场景key值的创建。
 */
public class SeckillKey extends BasePrefix {

    private SeckillKey(String prefix) {
        super(prefix);
    }

    public static SeckillKey isGoodsOver = new SeckillKey("go");
}
