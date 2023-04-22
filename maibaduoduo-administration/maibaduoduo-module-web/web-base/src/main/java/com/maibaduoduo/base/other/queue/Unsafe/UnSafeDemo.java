package com.maibaduoduo.base.other.queue.Unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
public class UnSafeDemo {
   private Unsafe unsafe = null;
   private volatile int value = 0;
   Lock lock = new ReentrantLock(true);
    /**
     * 通过反射获取Unsafe对象
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
   private Unsafe getUnSafe() throws NoSuchFieldException, IllegalAccessException {
       Field field = Unsafe.class.getDeclaredField("theUnsafe");
       field.setAccessible(true);
       Unsafe unsafe = (Unsafe) field.get(null);
       return unsafe;
   }
    public UnSafeDemo() throws NoSuchFieldException, IllegalAccessException {
        this.unsafe = this.getUnSafe();
    }

    /**
     * 修改内存中变量的值
     * @throws Exception
     */
    public  void unsafePutGetInt() throws Exception {
        class Student {
            private int age = 5;
            public int getAge() {
                return age;
            }
        }
        Student student = new Student();
        System.out.println(student.getAge());
        Field field = student.getClass().getDeclaredField("age");
        unsafe.putInt(student, unsafe.objectFieldOffset(field), 10);
        System.out.println(student.getAge());
    }

    /**
     * 在非堆上分配内存
     * @throws Exception
     */
    public  void unsafeAllocateMemory() throws Exception {
        int BYTE = 1;
        long address = unsafe.allocateMemory(BYTE);
        unsafe.putByte(address, (byte) 10);
        byte num = unsafe.getByte(address);
        System.out.println(num);
        unsafe.freeMemory(address);
    }

    /**
     * CAS
     * Unsafe类兄提供了compareAndswapsetObject等方法类实现原子性操作。
     * Unsafe类中提供了compareAndSwapObject()、compareAndSwapInt()和compareAndSwapLong()这三个方法用来实现对应的CAS原子操作
     * @throws Exception
     */
    public  void unsafeCAS() throws Exception {
        class MyAutomicInteger {
            //private volatile int value = 0;
            private Unsafe unsafe;
            private long offset;

            public MyAutomicInteger(Unsafe unsafe) throws Exception {
                this.unsafe = unsafe;
                this.offset = unsafe.objectFieldOffset(UnSafeDemo.class.getDeclaredField("value"));
            }

            public void increment() throws InterruptedException {
                int oldValue = value;
                //通过循环的方式设置变量值，直到设置成功，通过cas类实现保证值修改的原子性。
                if(oldValue==0){
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("oldValue="+oldValue+"-当前线程：" + Thread.currentThread().getId());
                }
                //for (;;) {
                    //if (unsafe.compareAndSwapInt(UnSafeDemo.this, offset, oldValue, oldValue + 1)) {
                        //System.out.println("当前线程："+Thread.currentThread().getId()+" oldValue="+oldValue);
                        //break;
                   // }
                    lock.lock();
                    oldValue =value + 1;
                    value = oldValue;
                   lock.unlock();
               // }
            }

            /**
             * 返回当前设置的新值
             * @return
             */
            public int getAndIncrement() {
                int oldValue = value;

                for (;;) {
                    if (unsafe.compareAndSwapInt(UnSafeDemo.this, offset, oldValue, oldValue + 1)) {
                        System.out.println("当前线程："+Thread.currentThread().getId()+" oldValue="+oldValue);
                        return oldValue;
                    }

                    oldValue = value;
                }
            }

            public int getValue() {
                return value;
            }
        }

        MyAutomicInteger myAutomicInteger = new MyAutomicInteger(unsafe);
        myAutomicInteger.increment();
        System.out.println("当前线程："+Thread.currentThread().getId()+" New值="+myAutomicInteger.getValue());

       /* for (int i = 0; i < 10; i++) {
            System.out.println(myAutomicInteger.getAndIncrement());
        }

        System.out.println(myAutomicInteger.getValue());*/
    }

    public static void main(String[] args) throws Exception {
          UnSafeDemo unSafeDemo = new UnSafeDemo();
         // unSafeDemo.unsafePutGetInt();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        unSafeDemo.unsafeCAS();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).cancel(true);
        }
        executorService.shutdown();
        //executorService.awaitTermination(-1, TimeUnit.SECONDS);
    }
    /**
     ==============非公平锁Out：~
     * oldValue=0-当前线程：13
     oldValue=0-当前线程：12
     oldValue=0-当前线程：11
     oldValue=0-当前线程：14
     当前线程：11 New值=3
     当前线程：12 New值=2
     当前线程：13 New值=1
     当前线程：14 New值=4
     oldValue=0-当前线程：15
     oldValue=0-当前线程：16
     当前线程：15 New值=5
     oldValue=0-当前线程：20
     oldValue=0-当前线程：19
     oldValue=0-当前线程：18
     当前线程：16 New值=6
     oldValue=0-当前线程：17
     当前线程：17 New值=10
     当前线程：18 New值=9
     当前线程：19 New值=8
     当前线程：20 New值=7

     ===============公平锁out：~

     */
}
