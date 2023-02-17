#Kafka-Commands

#Install Kafka-Commands
===========================
1. download kafka from https://www.apache.org/dyn/closer.cgi?path=/kafka/2.7.0/kafka_2.12-2.7.0.tgz
2. extract it to a suitable location (e.g. G:\)
3. Our KAFKA_HOME : G:\kafka_2.12-2.7.0 (substitute it for your install location)
4. We are using windows 10, windows kafka scripts are located under KAFKA_HOME\bin\windows

#Follow the sequence to start kafka server with all defaults:

1. Start Zookeeper Server (it starts at port 2181)
>bin\windows\zookeeper-server-start.bat config\zookeeper.properties

2. start Kafka Cluster:
>bin\windows\kafka-server-start.bat config\server.properties

3. Create a Kafka Topic by name demo-topic
> bin\windows\kafka-topics.bat --create --topic demo-topic --bootstrap-server localhost:9092

4. bin\windows\kafka-topics.bat --list --topic demo-topic --bootstrap-server localhost:9092

5. bin\windows\kafka-topics.bat --describe --topic demo-topic --bootstrap-server localhost:9092

Topic: demo-topic       PartitionCount: 1       ReplicationFactor: 1    Configs: segment.bytes=1073741824
        Topic: demo-topic       Partition: 0    Leader: 0       Replicas: 0     Isr: 0
		
6. Send Message to topic using kafka-console-producer

>bin\windows\kafka-console-producer.bat --topic demo-topic --bootstrap-server localhost:9092
>hello
>how are you
>where were you till now
>Terminate batch job (Y/N)? n  (press ctrl+c to terminate the producer)

7. Read the messages from the topic using kafka-console-consumer
>bin\windows\kafka-console-consumer.bat --topic demo-topic --from-beginning --bootstrap-server localhost:9092
hello
how are you
where were you till now

8. shutdown Kafka-cluster
>bin\windows\kafka-server-stop.bat --bootstarp-server localhost:9092

9. Stop Zookeeper
>bin\windows\zookeeper-server-stop.bat

#Run Kafka for Every Project and task and create Log Files for each Project
Our Kafka instllation: G:\kafka_2.12-2.7.0
1. set KAFKA_HOME=G:\kafka_2.12-2.7.0
open windows terminal in admin mode and run
setx KAFKA_HOME=G:\kafka_2.12-2.7.0

2. modify %KAFKA_HOME%\config\zookeeper.propeties to point to 
dataDir=../tmp/zookeeper (this will create dataDir in the current folder from wherever you run Zookeeper)

3. modify %KAFKA_HOME%\config\server.properties
log.dirs=../tmp/kafka-logs

Create Scripts:

1. start ZooKeeper: %KAFKA_HOME%\bin\windows\zookeeper-server-start.cmd %KAFKA_HOME%\config\zookeeper.properties

2. start Kafka: %KAFKA_HOME%\bin\windows\kafka-server-start.cmd %KAFKA_HOME%\config\server.properties

3. Create Topic: %KAFKA_HOME%\bin\windows\kafka-topics.cmd --create --topic demo-topic --partitions 4 --replication-factor 1

4. stop Kafka: %KAFKA_HOME%\bin\windows\kafka-server-stop.cmd 
5. stop zookeeper: %KAFKA_HOME%\bin\windows\zookeeper-server-stop.cmd %KAFKA_HOME%\config\zookeeper.properties

it can be done for Linux and Unix following the similar procedure