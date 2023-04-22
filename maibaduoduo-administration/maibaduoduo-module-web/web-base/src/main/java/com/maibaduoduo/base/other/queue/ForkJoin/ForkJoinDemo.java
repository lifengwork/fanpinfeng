package com.maibaduoduo.base.other.queue.ForkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
public class ForkJoinDemo {
    public static void main(String[] args) {
        //生成产品数据
        System.out.println("main:生成产品数据");
        List<Product> products = new ArrayList<Product>(50000);
        double value = 0;
        for (int i = 0; i < 50000; i++) {
            Product p = new Product();
            double price = (100 * Math.random());
            value += price;
            p.setPrice(price);
            products.add(p);
        }
        System.out.println("main:产品数据价格总和：" + value);
        //创建TaskDemo对象来更新产品价格
        SumPriceTask task = new SumPriceTask(products, 0, products.size());

        //创建ForkJoinPool对象，并启动
        System.out.println("main:创建ForkJoinPool对象，并启动");
        ForkJoinPool pool = new ForkJoinPool();
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
        try {
            double sum = task.get();
            System.out.println("main:价格总和计算结果是：" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("main:退出");
    }
}

class SumPriceTask extends RecursiveTask<Double> {

    private List<Product> products;
    private int first;
    private int last;

    SumPriceTask(List<Product> products, int first, int last) {
        this.products = products;
        this.first = first;
        this.last = last;
    }

    @Override
    protected Double compute() {
        if (last - first < 10) {
            return sumPrice();
        } else {
            //如果大于10个元素，则进行拆分
            int middle = (last + first) / 2;
            SumPriceTask t1 = new SumPriceTask(products, first, middle + 1);
            SumPriceTask t2 = new SumPriceTask(products, middle + 1, last);
            invokeAll(t1, t2);
            double result = 0;
            try {
                result = t1.get() + t2.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    /**
     * 计算价格总和
     **/
    private double sumPrice() {
        double sum = 0;
        for (int i = first; i < last; i++) {
            sum += products.get(i).getPrice();
        }
        return sum;
    }
}
