# Apache Kafka Example using SpringBoot

Start zookeeper.start bat file like below
zookeeper-server-start.bat --XXXX(Zokeeper Location)

start kafka server
kafka-server-start.bat --XXXX(Kafka Location once Zookeeper Location)

Create Topic:
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 -topic nameOfTopic

Produce a message
kafka-console-producer.bat --broker-list localhost:9092 --topic nameOfTopic

Consume a message
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic nameOfTopic
