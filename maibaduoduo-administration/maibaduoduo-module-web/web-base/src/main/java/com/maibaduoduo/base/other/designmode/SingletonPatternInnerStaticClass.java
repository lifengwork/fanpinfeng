package com.maibaduoduo.base.other.designmode;

/**
 * Created by Administrator on 2019/11/6 0006.
 */
public class SingletonPatternInnerStaticClass {
    private SingletonPatternInnerStaticClass(){}
    private static class buildSingletonPattern{
        public static final SingletonPatternInnerStaticClass singletonPattern =  new SingletonPatternInnerStaticClass();
    }
    public static SingletonPatternInnerStaticClass instanceSingletonObject(){
        return buildSingletonPattern.singletonPattern;
    }

    public static void main(String[] args) {
        SingletonPatternInnerStaticClass.instanceSingletonObject();
    }
}
