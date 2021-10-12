# drinking

* abstract: source -> processor -> sink
* spring initializr:
    * Cloud Stream
    * Spring for Apache Kafka: for bind
    * Spring Web: for generation end-point by web
    * Lombok: for convenience

## local kafka

* docker-compose up -d
* docker-compose ps
* docker-compose down

## source generation

* http://localhost:8080/swagger-ui.html

## topic monitoring

* ./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
```
words-upper-group
words-console-out-group
```
* ./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group words-upper-group
```
GROUP             TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID                                                       HOST            CLIENT-ID
words-upper-group words-source    0          125             125             0               consumer-words-upper-group-2-0966a14e-b83c-4815-be1b-1f88ef296e9c /172.18.0.1     consumer-words-upper-group-2
```
```
Consumer group 'words-upper-group' has no active members.

GROUP             TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID     HOST            CLIENT-ID
words-upper-group words-source    0          125             126             1               -               -               -
```
