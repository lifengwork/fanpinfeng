**项目说明** 
- renren-generator是人人开源项目的代码生成器，可在线生成entity、xml、dao、service、html、js、sql代码，减少70%以上的开发任务
<br> 

 **本地部署**
- 通过git下载源码
- 修改application.yml，更新MySQL账号和密码、数据库名称
- Eclipse、IDEA运行RenrenApplication.java，则可启动项目
- 项目访问路径：http://localhost



<driver-url>jdbc:mysql://localhost:3306/visit-system</driver-url>
		<driver-class>com.mysql.jdbc.Driver</driver-class>
		<driver-properties>
			<property name="employee" value="root"/>
			<property name="password" value="123456"/>
</driver-properties>