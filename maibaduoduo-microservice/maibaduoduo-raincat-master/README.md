#### A strongly consistent distributed transaction framework

 本工程是对开源的raincat项目进行的二次开发，简化了扩展的内容，为分布式事务强一致性实现，通过协调本地事务管理，对所有事务一起提交。
 主要用在分布式环境下，支付，转账等有所有事务一起提交，实现数据的一致性。

# Modules

  * raincat-admin: Transaction log management background
  
  * raincat-annotation : Framework common annotation

  * raincat-common :  Framework common class
  
  * raincat-core : Framework core package (annotation processing, log storage...)              
  
  * raincat-dashboard : Management background front-end
  
  * raincat-dubbo : Support for the dubbo framework Less than 2.7 version
  
  * raincat-motan : Support for the motan rpc framework
  
  * raincat-springcloud : Support for the spring cloud rpc framework
  
  * raincat-spring-boot-starter : Support for the spring boot starter
  
  * raincat-sample : Examples using the raincat framework


#  Features
   
   *  All spring versions are supported and Seamless integration
   
   *  Provides support for the springcloud dubbo motan RPC framework
   
   *  Provides integration of the spring boot starter approach
   
   *  Support Nested transaction 
   
   *  Local transaction storage support :  redis mongodb zookeeper file mysql
   
   *  Transaction log serialization support : java hessian kryo protostuff
   
   *  Spi extension : Users can customize the storage of serialization and transaction logs

# Transaction Role

  * Transaction starter : `@TxTransaction` for The entry point of the first section
  
  * Transaction participant : Rpc invoker in the method (Add `@TxTransaction`)
  
  * Transaction coordinator : Coordinate the rollback of commit transactions
   

# Raincat-Manager
 
  It is the coordinator of the transaction and USES netty communication framework to communicate with participants and initiators.
  
  Use eureka as a registry to support cluster deployment.
  
  Use redis to store transaction information.
  
  It has to start early.


# Prerequisite 

  * You must use jdk1.8 +
  
  * You must be a user of the spring framework
  
  * You must use one of the dubbo, motan, and springcloud RPC frameworks
  

# About
 
  raincat is A strongly consistent distributed transaction framework.
  
  Good concurrency support, blocking spring transaction thread commit.
  
  When the execution of the slice is complete and there is no exception, 
  
  the submission command is sent asynchronously by the coordinator to achieve strong consistency.

