# 说明
- 架构全局公共类，配置类，异常类，banner等。

#麦罢哚哆架构技术栈：
- admin server 服务监控
- apollo server 分布式配置中心
- gateway server 服务网关,实现了资源访问认证，定制路由，限流，资源缓存，请求结果集聚合。
- eureka server 服务注册/发现中心
- zipkin server 服务调用链路日志
- turbine server 收集stream.hystrix 默认收集所有服务
- job server quartz 定时任务可以集群
- lock service 集成了redis和zookeeper分布式锁，以及限流工具类
- myth 使用RabbitMQ是先分布式事务最终一致性
- raincat 使用2pc+补偿，实现分布式事务，强一致性
- 使用ribbon进行服务的请求负载均衡，
- hystrix对服务的请求进行容错处理，涉及到熔断，降级，搜集服务的使用指标，服务的请求合并，缓存等。
- JWT用户认证
- XSS 安全校验（sql注入，XSS过滤）
- 集成了CORS 服务跨域请求
- docker 自动化部署，docker-compose，服务编排，k8s容器管理。
- 日志搜集分析处理，采用 logback+rabbitmq/kafka+ELK
- 通过shiro实现用户的认证和授权
- 使用redis缓存，可以集群
- J2Cache 实现两级缓存框架
- 关系存放使用mysql数据库
- 连接池采用druid
- fastdfs server 分布式文件存储服务
- 消息队列 RabbitMQ
- 文档存储mongdb
- 持久层框架mybatis
- 大文件/断点/分片续传
# 内部集成代码生成器，
格式： entity、xml、dao、service、html、js、sql等，前端框架结合vue
# 服务的基本结构     
  common 存放本服务公用的资源
  core 存放本部服务核心的类，dao，数据对象，视图对象，服务接口等
  facade 提供外部服务使用
  service 相关拦截器，AOP，服务接口的实现等
# 接口的测试采用
 swaggerui和swaggerEdit
#  微服务：
   - 租户服务/
   - 订单服务/（订单状态，审核状态，付款状态，物流状态，结算状态）
         状态机：现状，动作，次态
         现状：订单当前所处的状态
         动作：订单将要转变的操作
         次态：订单将要转变的新状态
   - 支付服务/
   - 仓储服务/
   - 结算服务/
   - 财务服务/
   - 商品服务/
   - 系统服务/（用户，权限等相关设置）
   - 店铺服务/
   - 促销服务/
   - 集市服务/
   - 供应服务/
   - 采购服务/
   - 配送服务/
   - 账户服务/
   - 计费服务/
   - 风控服务/
   - 会员服务/
   - 加工服务（接受需求，设计产品，分派加工任务）/
   - 报表服务/
          
     
