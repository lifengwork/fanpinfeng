package com.maibaduoduo.base.other.designmode;

public class OuterA {

    public void someOuterClassMethod(){
         new InnerB();
    }

    class  InnerB{}

    public static void main(String[] args) {
        OuterA outerA = new OuterA();
        //new InnerB(); 不能引用非静态类
        outerA.new InnerB();
    }

}
