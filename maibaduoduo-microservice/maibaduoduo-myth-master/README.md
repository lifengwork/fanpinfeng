myth  
================

本系统是开源的myth项目的二次开发，简化了部分扩展的实现，通过MQ的方式来实现在分布式环境下的事务的最终一致性。
主要用在高并发环境，对数据有一致性要求，但是能承受最终一致性的需求场景。
解决的事务问题：
- down机重新发起事务
- 超时定时task重新发起（事务发起方）
- 异常定时task重新发起（事务发起方）
- 失败的事务，定时task会重新执行业务处理（事务提供者），执行本地事务。（mq事务补偿）

目标，解决不同系统的的数据状态最终达到一致。

#####  采用消息队列解决分布式事务的开源框架, 基于java语言来开发（JDK1.8），支持dubbo，springcloud,motan等rpc框架进行分布式事务。

#  Features

  * ##### 天然无缝集成 spring-boot-starter 。
  
  * ##### RPC框架支持 : dubbo,motan,springcloud。

  * ##### 消息中间件支持 : jms(activimq),amqp(rabbitmq),kafka,roceketmq。

  * ##### 本地事务存储支持 : redis,mogondb,zookeeper,file,mysql。

  * ##### 事务日志序列化支持 ：java，hessian，kryo，protostuff。

  * ##### 采用Aspect AOP 切面思想与Spring无缝集成，天然支持集群,高可用,高并发。

  * #####  配置简单，集成简单，源码简洁，稳定性高，已在生产环境使用。

  * ##### 内置经典的分布式事务场景demo工程，并有swagger-ui可视化界面可以快速体验。


#  源码解析

  * ## https://juejin.im/post/5a5c63986fb9a01cb64ec517 
  
#  视频详解

  * ## 环境搭建以及运行 : http://www.iqiyi.com/w_19rw5zuigl.html
  * ## 原理讲解（1）：http://www.iqiyi.com/w_19rw5ztpkh.html
  * ## 原理讲解（2）：http://www.iqiyi.com/w_19rw5zslm1.html
  


# Prerequisite

  *   #### JDK 1.8+

  *   #### Maven 3.2.x

  *   #### Git

  *   ####  RPC framework dubbo or motan or springcloud。

  *   #### Message Oriented Middleware


# Quick Start

* #### Clone & Build
   ```
   > git clone https://github.com/yu199195/myth.git

   > cd myth

   > mvn -DskipTests clean install -U
   ```

* #### execute this sql       
 https://github.com/yu199195/myth/tree/master/myth-demo/sql/myth-mysql-demo.sql

* #### Find the RPC framework that works for you
 https://github.com/yu199195/myth/tree/master/myth-demo

# FAQ

* ### 为什么我下载的代码后，用idea打开没有相应的get set 方法呢？
   ##### 答：因为框架使用了Lombok包，它是在编译的时期，自动生成get set方法，并不影响运行，如果觉得提示错误难受，请自行下载lombok包插件，[lombok官网](http://projectlombok.org/)

* ### 为什么我运行demo工程，找不到applicationContent.xml呢？
  ##### 答：请设置项目的资源文件夹。
  
* ### 为什么我启动myth-admin项目的时候，会报mongo 集群连接错误呢？
  ##### 答：这是因为项目里面有mongo代码，spring boot会自动配置，该错误没有关系，只要admin项目能正常启动就行。
