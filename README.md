# aggregation
聚合项目，大杂烩

#### kafka
```
创建topic并指定topic参数
1、kafka-topics.bat --create --topic spark-test --zookeeper localhost:2181 --partitions 3 --replication-factor 1

查看topic消息
2、kafka-topics.bat --describe --zookeeper localhost:2181 --topic spark-test 

查看消息消费情况
3、kafka-run-class.bat kafka.admin.ConsumerGroupCommand  --bootstrap-server localhost:9092  --describe --group kafka01

4、使用工具查看消息剩余的消息 下面 --zk是指定了zookeeper所以监控的消费端必须是连接到zookeeper否则图形上显示没有激活的consumer
java -cp KafkaOffsetMonitor-assembly-0.2.0.jar com.quantifind.kafka.offsetapp.OffsetGetterWeb --zk 192.168.2.150:2181 --port 8088 --refresh 10.seconds --retain 2.days<br/>

http://localhost:8088/
```

#### jdk1.7 NIO2 
+ 支持 walkFileTree、FileVisitor（遍历文件/目录） 参见：#com.gochinatv.cdn.api.file.JDK7FilesVisitor
+ 平台无关的文件系统访问支持（Path、Paths、Files）参见：http://blog.csdn.net/lirx_tech/article/details/51416672
+ 文件属性 参见：#java.nio.file.attribute ref: http://blog.csdn.net/lirx_tech/article/details/51428238
+ 监控文件变化 WatchService、WatchKey（监控文件变化）参见：#com.gochinatv.cdn.api.file.JDK7WatchFiles

#### rabbitmq

#### logback日志级别输出
+ com.gochinatv.cdn.api.logger
+ classpath:logback.xml

#### zk 多client选leader
+ 参考 com.gochinatv.cdn.api.zk  （ TestZK TestZK2  TestZK3  TestZK4相当于4个节点，启动之后选主）

#### elastic-job
+ 分布式任务框架

###TODO
#### elasticsearch
