#shiro实现原理：
   设计到的核心对象：  
    1，subject  （获取需要认证的主题）
    2，SecurityManager  （管理认证信息（通过realms（redis、mysql等数据源）实现）和token信息）
    3，realms  （具体的权限控制）
    
   shiro就是一个很富有的过滤器，在和spring集成时，通过委托代理的方式，把shiro交友spring容器来管理。
    实现：  
    1，编写一个过滤器，引入shiro。  
    2，编写realm来实现权限的控制。  
    3，创建token  
   
   具体的交互流程   
   
   类的交互
   SecurityManager类体系，
              可以获取subject类体系、realm类体系等类的信息。