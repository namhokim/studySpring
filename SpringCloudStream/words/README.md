# words

* abstract: source -> processor -> sink
* spring initializr:
    * Cloud Stream
    * Spring for Apache Kafka: for bind
    * Spring Web: for generation end-point by web
    * Lombok: for convenience

## Local kafka start and terminate

```shell
$ docker-compose up -d
$ docker-compose ps

      Name                     Command               State                                   Ports                                 
-----------------------------------------------------------------------------------------------------------------------------------
words_kafka_1       /opt/bitnami/scripts/kafka ...   Up      0.0.0.0:9092->9092/tcp,:::9092->9092/tcp                              
words_zookeeper_1   /opt/bitnami/scripts/zooke ...   Up      0.0.0.0:2181->2181/tcp,:::2181->2181/tcp, 2888/tcp, 3888/tcp, 8080/tcp

$ docker-compose down
```

## source generation

* http://localhost:5000/swagger-ui.html

## topic monitoring

* Use https://kafka.apache.org/downloads or Bitnami Docker's image has script files already.
  * location: /opt/bitnami/kafka/bin
```shell
$ docker ps

CONTAINER ID   IMAGE                   COMMAND                  CREATED          STATUS          PORTS                                                                     NAMES
fa8ec08e2ee5   bitnami/kafka:2         "/opt/bitnami/script…"   11 minutes ago   Up 11 minutes   0.0.0.0:9092->9092/tcp, :::9092->9092/tcp                                 words_kafka_1
fc0ba093b001   bitnami/zookeeper:3.7   "/opt/bitnami/script…"   11 minutes ago   Up 11 minutes   2888/tcp, 3888/tcp, 0.0.0.0:2181->2181/tcp, :::2181->2181/tcp, 8080/tcp   words_zookeeper_1

$ docker exec -it fa8ec08e2ee5 /bin/bash
$ cd /opt/bitnami/kafka/bin
```

### List up kafka consumer groups
```shell
$ ./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
words-upper-group
words-console-out-group
```

### Describe specific consumer groups
```shell
$ ./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group words-upper-group
GROUP             TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID                                                       HOST            CLIENT-ID
words-upper-group words-source    0          125             125             0               consumer-words-upper-group-2-0966a14e-b83c-4815-be1b-1f88ef296e9c /172.18.0.1     consumer-words-upper-group-2
```
```shell
$ ./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group words-upper-group

Consumer group 'words-upper-group' has no active members.

GROUP             TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID     HOST            CLIENT-ID
words-upper-group words-source    0          125             126             1               -               -               -
```
