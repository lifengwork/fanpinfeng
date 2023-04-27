# SAAS系统设计研发交流


本SAAS系统是一个通用的SAAS系统，可以用在加工制造业相关业务开发以及数据采集、数据分析等，
当前版本实现了基本功能包括租户运营端和业务端。
采用微服务架构（SpringCloud），基于Java语言（JDK1.8），采用RabbitMQ消息队列并支持MQTT协议（采集设备端的数据以及与设备进行交互），采用二级缓存方案（ehcache+redis，提高吞吐量、高可用），使用关系数据库Mysql作为业务数据存储方案（动态数据源、读写分离）；
另外使用轻量级Shiro作为认证授权以及会话管理，采用JWT生成TOKEN用在移动端作为访问认证解决方案；
系统也实现了网关动态路由、负载均衡、服务容错回调以及断路器；微服务组件方面起见采用开源NACOS做为注册中心和配置中心；
分布式事务使用MQ的方式来实现在分布式环境下的事务的最终一致性，主要用在高并发环境，对数据有一致性要求，能承受最终一致性的需求场景。
.....

<br>

**具有如下** 
- 租户类型支持独立数据源和共享数据源（Cloumn）的租户数据隔离。
- 支持根据租户类型动态选择租户数据源
- 根据租户类型动态生成租户数据源以及初始化租户业务端登录账号，数据源初始化，租户登录授权等。
- 实现前后端分离，通过token（JWT）进行数据交互
- 系统管理管理部分实现了菜单管理、用户管理、角色管理、权限绑定等
- 前端使用Vue2.x，前端体验可以单独演化
- 也集成了业务相关的基本代码生成功能，设计controller、service、dao以及前端vue基本页面代码。
- 引入XXL-JOB定时任务，可动态完成任务的添加、修改、删除、暂停、恢复及日志查看等功能
- 引入APIFACADE模式，采用FEGIN来调用接口可以进行服务之间的交互，和实际业务端接口解耦便于开发维护
- 请求数据校验采用Hibernate Validator校验框架
- 实现了跨域、已经XSS、sql注入解决方案
- 文件存储采用MiniIo作为分布式文件操作解决方案
- 实现了微服务组件注册中心、配置中心、微服务ADMINSEVICE管理监控服务的运行状态，以及使用HYSTRIX来监控服务的调用情况。
- 引入swagger2文档支持，便于接口的调试和阅读
- 实现了分布式事务（消息补偿机制）数据最终一致性解决方案
- 使用Disruptor非阻塞环形队列高效处理事件单机可以达百万
- 支持高缓存本地+集中式缓存
- .....
<br> 

# 运行准备

  *   #### JDK 1.8+
  *   #### Maven 3.3+
  *   #### Git
  *   #### Idea/Eclipse 
  *   #### Nacos、Redis、RabbitMQ、MQTT、Mysql、Tomcat
  *   #### MinIo文件服务器

# 系统结构
  SASS-FANPINFENG核心结构入如下:  
  |--maibaduoduo-administration saas运营后端系统,租户管理、认证、授权，服务订购开通等  
  |--|--maibaduoduo-module-web    
  |--|--| web-base  基本模块  
  |--|--| web-handle 请求处理  
  |--maibaduoduo-microservice   saas业务端系统  
  |--|--maibaduoduo-admin-master     微服务基本组件，注册发现、配置中心、服务监控等  
  |--|--maibaduoduo-business-master  业务服务，订单服务、库存服务、执行计划服务等  
  |--|--maibaduoduo-master           微服务基础配置  
  |--|--maibaiduoduo-common-master   通用组件，涉及动态数据源、RabbitMQ等组件  
  |--maibaduoduo-admin-vue      saas业务前端系统  

# 核心技术选型
 - SpringCloud  微服务所需组件
 - Springboot   便捷服务启动发布
 - Redis、ehcache  数据缓存
 - RabbitMq    支持高并发消息队列
 - Mysql     关系行数据存储数据
 - Vue2      前端框架
 - Nacos     服务注册发现、数据配置
 - Fegin(Ribbon、Hystrix)  负载均衡、服务容错回调、断路器等
 - Mqtt3     高效互联网协议
 - Apache Shiro    轻量级认证授权会话管理
 - JWT(TOKEN)    登录认证
 - MyBatisPlus   数据持久层框架
 - Druid    数据库链接池
 - XXL-JOB     定时任务管理
 - logback     日志记录
 - Swagger2    接口文档管理

* #### Clone & Build
   ```
   > git clone git@github.com:lifengwork/fanpinfeng.git
   
   ```
* #### 接口文档访问地址
   ```
      http://localhost:port/doc.html		
 
  ```
* #### execute this sql       
  

# FAQ

* ### 说明
  
