# 线程池ThreadPoolExecutor创建
       方法（1）：execute(Runnable) 没有返回值。
       方法（2）：submit([Runnable]|[Callable]) 
   
#异常处理：
         线程池任务异常处理
         1,线程异常处理器：
           （1）直接任务中 try{}cache
           （2）覆盖afterExcec方法
           （3） 覆盖Thread中的异常处理器
            对于submit处理异常的方法时直接在通过try{}cach{}，另外在FutrueTask调用get方法时采用抛出，
             因此也可以在调用给方法时，处理异常。
  
# 异常拒绝策略  

       /*
        * <p>
        * （1）queueCapacity 设置阻塞队列的容量，如果不设置会创建一个NONFAIR策略的异步队列。
        * （2）rejectedExecutionHandler拒绝策略：</p>
        * <ol>
        * <li> In the default {@link ThreadPoolExecutor.AbortPolicy},
        *      直接抛出异常
        * </li>
        * <li> In {@link ThreadPoolExecutor.CallerRunsPolicy},
        *      使用调用者线程运行任务
        * </li>
        * <li> In {@link ThreadPoolExecutor.DiscardPolicy},
        *     丢弃
        * </li>
        * <li>In {@link ThreadPoolExecutor.DiscardOldestPolicy},
        *     丢弃队列的中时间最长的任务，运行当前任务
        * </li>
        * </ol>
        *
        * @return
        */
        
 # 工作队列选择
          ArrayBlockQueue               