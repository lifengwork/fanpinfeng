package com.maibaduoduo.base.other.queue.ForkJoin;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
public class ForkJoinDemo2 {
    public static ThreadPoolExecutor getExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5), new ThreadPoolExecutor.AbortPolicy());
        //捕获处理失败的异常
        executor.setRejectedExecutionHandler((r, executor1) -> {
            System.out.println("系统繁忙,请求失败,请稍后再试。");
        });
        return executor;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Product> products = new ArrayList<Product>();
        SumListTask task = new SumListTask(products, 0);
        System.out.println("main:创建ForkJoinPool对象，并启动");
        ForkJoinPool pool = new ForkJoinPool(6);
        pool.execute(task);
        System.out.println("main:输出线程池信息");
        do {
            System.out.println("main:线程数量：" + pool.getActiveThreadCount());
            System.out.println("main:偷窃数量：" + pool.getStealCount());
            System.out.println("main:并行级别：" + pool.getParallelism());
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());
        //关闭线程池
        System.out.println("main:关闭线程池");
        pool.shutdown();
        //等待线程执行完成
        try {
            pool.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //输出计算结果
        List<Product> sumListResult = task.get();
        System.out.println("main:请求获取的结果：" + JSON.toJSONString(sumListResult));

        System.out.println("main:退出");
    }
}

class SumListTask extends RecursiveTask<List> {
    List<SumListTask> listTasks = new ArrayList<SumListTask>();
    private List<Product> products;
    private int state;

    SumListTask(List<Product> products, int state) {
        this.products = products;
        this.state = state;
    }

    @Override
    protected List compute() {
        try {
            switch (state) {
                case 1:
                    return sumList(state);
                case 2:
                    return sumList(state);
                case 3:
                    return sumList(state);
                default:
                    SumListTask t1 = new SumListTask(products, 1);
                    SumListTask t2 = new SumListTask(products, 2);
                    SumListTask t3 = new SumListTask(products, 3);
                    listTasks.add(t1);
                    listTasks.add(t2);
                    listTasks.add(t3);
                    invokeAll(listTasks);
                    try {
                       for(SumListTask sumListTask:listTasks){
                           products.addAll(sumListTask.get());
                       }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return products;
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 返回结果集
     **/
    private List<Product> sumList(int state) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExcuptor = ForkJoinDemo2.getExecutor();
        Future<List<Product>> futureList = threadPoolExcuptor.submit(() -> {
            List<Product> products1 = new ArrayList<>();
            Product product = null;
            for (int i = 0; i < 5; i++) {
                product = new Product();
                product.setPrice(Double.valueOf("5." + state + "d"));
                products1.add(product);
            }
            return products1;
        });
        threadPoolExcuptor.shutdown();
        return futureList.get();
    }
}