package com.maibaduoduo.base.other.RatelimiterDemo;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/11/7 0007.
 */
public class ReteLimiterDemo {
    private static Logger log = LoggerFactory.getLogger(ReteLimiterDemo.class);
    private static RateLimiter rateLimiter = RateLimiter.create(10000);//每秒10000个,等待时间为1/10000s
    private static RateLimiter rateLimiter5 = RateLimiter.create(5);//每秒中5个，等待时间为1/5s

    public static void main(String[] args) throws InterruptedException {
        Integer sum = 1000;
        for (int i = 0; i < 10; i++) {//模拟请求数量
               TimeUnit.MILLISECONDS.sleep(100);
               //Double timewate =  rateLimiter.acquire();//等待时间为1/10000
               //Double timewate =  rateLimiter.acquire(6543);//等待时间为6543*（1/10000）
               //System.out.println("第" + i + "个通过。==等待时间："+String.format("%.15f",timewate)+" 令牌数："+rateLimiter.getRate());
               if(rateLimiter.tryAcquire(6543)){//等待时间为6543*（1/10000）
                   System.out.println("第" + i + "个通过。==等待时间："+" 令牌数："+rateLimiter.getRate());
               }else{
                   /**
                    *  TO DO
                    */
                   System.out.println("请求超时。");//在这里执行降级逻辑。
               }

               //System.out.println("第" + i + "个通过。==等待时间：");
        }
    }

}
