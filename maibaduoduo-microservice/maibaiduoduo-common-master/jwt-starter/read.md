  用户登录时，会将页面输入的账户密码传入对应的微服务中，而现在微服务中还没有当前用户的认证，由此微服务再转发给注册中心，注册中心再访问用户中心，用户中心通过调取数据库中的信息，

对传过来的信息进行核对，核实正确后使用非对称加密形成一个公钥与私钥，私钥自己保存，公钥分发给各个微服务，然后用户再次登录时，当前的微服务就拥有公钥，通过公钥解密就可以验证用户

是否正确。正确就放行通过。

　　jwt含有三个部分，分别是Header，载荷，签名。

　　header：包含有jwt 当前的声明的定义，还有base64算法。

　　载荷：主要存放当前的有效信息。

　　签名：主要是对header与负载中的有效信息使用密钥加密生成token。



    授权流程：

        1、用户请求登录，携带用户名密码到授权中心

        2、授权中心携带用户名密码，到用户中心查询用户

        3、查询如果正确，生成JWT凭证

        4、返回JWT给用户

    鉴权流程：

        1、用户请求某微服务功能，携带JWT

        2、微服务将jwt交给授权中心校验

        3、授权中心返回校验结果到微服务

        4、微服务判断校验结果，成功或失败

        5、失败则直接返回401

        6、成功则处理业务并返回