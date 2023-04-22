package com.maibaduoduo.base.other.queue.ExecutorDemo;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
public class CompletionServiceDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        /**
         * 内部维护11个线程的线程池
         */
        //ExecutorService exec = Executors.newFixedThreadPool(11);
        ThreadPoolExecutor exec = new ThreadPoolExecutor(5, 10, 10, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5),new ThreadPoolExecutor.AbortPolicy());
        /**
         * 容量为10的阻塞队列
         */
        final BlockingQueue<Future<Integer>> queue = new LinkedBlockingDeque<Future<Integer>>(10);
        //实例化CompletionService
        final CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(exec, queue);
        /**
         * 模拟瞬间产生10个任务，且每个任务执行时间不一致
         */
        for (int i = 0; i < 20; i++)
        {
            try{
            completionService.submit(new Callable<Integer>() {
                    @Override
                    public Integer call() throws InterruptedException {
                        int ran = new Random().nextInt(1000);
                           Thread.sleep(ran);
                           System.out.println(Thread.currentThread().getName() + " 休息了 " + ran);
                        return ran;
                    }
            });
            }catch(Exception e){
                System.out.println("系统繁忙,请求失败,请稍后再试。");
            }
        }
        /**
         * 立即输出结果
         */
        for (int i = 0; i < 10; i++)
        {
            try
            {
                //谁最先执行完成，直接返回
                Future<Integer> f = completionService.take();
                System.out.println(f.get());
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } catch (ExecutionException e)
            {
                e.printStackTrace();
            }
        }

        exec.shutdown();

    }
}
