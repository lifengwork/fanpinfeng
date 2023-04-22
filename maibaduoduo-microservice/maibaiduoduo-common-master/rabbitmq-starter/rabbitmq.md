1，UI-port:15672
2，安装后端服务页面插件：
 打开节点：  ./rabbitmqctl start_app
配置管理模块：./rabbitmq-plugins enable rabbitmq_management
查看状态：./rabbitmqctl status
查看用户列表: ./rabbitmqctl list_users
添加用户：
./rabbitmqctl add_user saas 123456
./rabbitmqctl set_user_tags saas administrator
通过命令查看MQTT插件的启动情况: ./rabbitmq-plugins list
启动mqtt插件：./rabbitmq-plugins enable rabbitmq_mqtt
给用户分配读写权限：
./rabbitmqctl set_permissions -p / saas ".*" ".*" ".*"
   Setting permissions for user "saas" in vhost "/" ...
