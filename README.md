# kafka-driver-user-location-app

Here, the driver publishes the location to the kafka server and the user listens to the location information. Just random locations from driver values send to kafka-topic. And in the user end, listen that locations value
using kafka listener.

### Demo Video file name: screen-capture.webm

## Run kafka using Docker:
1. docker run -itd --name kafkaContainer -p 9092:9092 apache/kafka

# Installing kafka in windows:

1. Download kafka
2. copy to c directory
3. need to change properties file and update log path of zookeeper and server properties files.
4. for server: log.dirs=c:\kafka\kafka-logs
5. for zookeeper: dataDir=c:\kafka\zookeeper-data
6. then run zookeeper and server in parallel cmd.
7. .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
8. .\bin\windows\kafka-server-start.bat .\config\server.properties

create topic:
#### .\bin\windows\kafka-topics.bat --create --topic topic-driver-locations --bootstrap-server localhost:9092

topic details:
#### .\bin\windows\kafka-topics.bat --describe --topic topic-driver-locations --bootstrap-server localhost:9092

