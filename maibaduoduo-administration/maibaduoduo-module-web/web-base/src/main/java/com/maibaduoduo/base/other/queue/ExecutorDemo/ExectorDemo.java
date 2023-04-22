package com.maibaduoduo.base.other.queue.ExecutorDemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试线程池的任务拒绝策略
 * Created by Administrator on 2019/10/31 0031.
 */
public class ExectorDemo {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        int corePoolSize = 1;
        int maximumPoolSize = 1;
        BlockingQueue queue = new ArrayBlockingQueue<Runnable>(1);//workqueue,用来存放超出线程池最大容量的线程
        ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 0, TimeUnit.SECONDS, queue);
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//AbortPolicy()//DiscardPolicy//将任务分给调用线程来执行(CallerRunsPolicy)。
        for (int i = 0; i < 10; i++) {
            final int index = i;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    log(Thread.currentThread().getName() + "begin run task :" + index);
                    try {
                       TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log(Thread.currentThread().getName() + " finish run  task :" + index);
                }
            });
        }
        log("main thread before sleep!!!");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("before shutdown()");
        pool.shutdown();
        log("after shutdown(),pool.isTerminated=" + pool.isTerminated());
        try {
            pool.awaitTermination(1000L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("now,pool.isTerminated=" + pool.isTerminated());
    }
    protected static void log(String string) {
        System.out.println(sdf.format(new Date()) + "  " + string);
    }
/**
 *   CallerRunsPolicy ：将任务分给调用线程来执行。
 *   2019-10-31 11:23:13  mainbegin run task :2
     2019-10-31 11:23:13  pool-1-thread-1begin run task :0
     2019-10-31 11:23:15  pool-1-thread-1 finish run  task :0
     2019-10-31 11:23:15  main finish run  task :2
     2019-10-31 11:23:15  mainbegin run task :3
     2019-10-31 11:23:15  pool-1-thread-1begin run task :1
     2019-10-31 11:23:17  main finish run  task :3
     2019-10-31 11:23:17  pool-1-thread-1 finish run  task :1
     2019-10-31 11:23:17  mainbegin run task :5
     2019-10-31 11:23:17  pool-1-thread-1begin run task :4
     2019-10-31 11:23:19  pool-1-thread-1 finish run  task :4
     2019-10-31 11:23:19  main finish run  task :5
     2019-10-31 11:23:19  pool-1-thread-1begin run task :6
     2019-10-31 11:23:19  mainbegin run task :7
     2019-10-31 11:23:21  pool-1-thread-1 finish run  task :6
     2019-10-31 11:23:21  main finish run  task :7
     2019-10-31 11:23:21  mainbegin run task :9
     2019-10-31 11:23:21  pool-1-thread-1begin run task :8
     2019-10-31 11:23:23  pool-1-thread-1 finish run  task :8
     2019-10-31 11:23:23  main finish run  task :9
     2019-10-31 11:23:23  main thread before sleep!!!
     2019-10-31 11:23:27  before shutdown()
     2019-10-31 11:23:27  after shutdown(),pool.isTerminated=false
     2019-10-31 11:23:27  now,pool.isTerminated=true
 *
 */
}
