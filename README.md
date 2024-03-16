# kafka-driver-user-location-app

Here, Just random locations from driver values send to kafka-topic. And in the user end, listen that locations values
using kafka listener.

# Installing kafka in windows:

1. Download kafka
2. copy to c directory
3. need to change properties file and update log path of zookeeper and server properties files.
4. for server: log.dirs=c:\kafka\kafka-logs
5. for zookeeper: dataDir=c:\kafka\zookeeper-data
6. then run zookeeper and server in parallel cmd.
7. .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
8. .\bin\windows\kafka-server-start.bat .\config\server.properties

#create topic
bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092

#details
.\bin\windows\kafka-topics.bat --describe --topic topic-driver-locations --bootstrap-server localhost:9092