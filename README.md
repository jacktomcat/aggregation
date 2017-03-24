# aggregation
聚合项目，大杂烩



####kafka
```
创建topic并指定topic参数
1、kafka-topics.bat --create --topic spark-test --zookeeper localhost:2181 --partitions 3 --replication-factor 1

查看topic消息
2、kafka-topics.bat --describe --zookeeper localhost:2181 --topic spark-test 

查看消息消费情况
3、kafka-run-class.bat kafka.admin.ConsumerGroupCommand  --bootstrap-server localhost:9092  --describe --group kafka01
```

####TODO
#####1、查看kafka的消费情况，消息积压情况等等./kafka-run-class.sh