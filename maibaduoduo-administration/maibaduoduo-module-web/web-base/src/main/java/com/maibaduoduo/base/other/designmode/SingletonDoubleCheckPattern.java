package com.maibaduoduo.base.other.designmode;

/**
 * 单例双重检查实现。
 * Created by Administrator on 2019/11/6 0006.
 */
public class SingletonDoubleCheckPattern {
    private SingletonDoubleCheckPattern(){}
    //并发环境下值修改时，值时时可见性;
    private static volatile SingletonDoubleCheckPattern singletonDoubleCheckPattern = null;
    public static SingletonDoubleCheckPattern instanceSingletonObject(){
        //多线程情况下，防止多次实例化，进行二次判断双重检查。
         if(null==singletonDoubleCheckPattern){
             synchronized (SingletonDoubleCheckPattern.class){
                if(null==singletonDoubleCheckPattern){
                    singletonDoubleCheckPattern = new SingletonDoubleCheckPattern();
                }
             }
         }
        return singletonDoubleCheckPattern;
    }
}
