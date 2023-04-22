namingLoadCacheAtStart: true   # 开启本地服务列表缓存，生产环境须开启
Nacos 存在本地文件缓存机制，nacos-client 在接收到 nacos-server 的服务推送之后，会在内存中保存一份，随后会落盘存储一份快照。snapshot 默认的存储路径为：{USER_HOME}/nacos/naming/ 中