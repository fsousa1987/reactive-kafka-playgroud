package com.francisco.reactive_kafka_playgroud.sec01;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.List;
import java.util.Map;

/*
    goal: to demo a simple Kafka consumer using reactor Kafka

    producer --> Kafka broker <---> consumer
*/
public class Lec01KafkaConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(Lec01KafkaConsumer.class);

    static void main() {

        var consumerConfig = Map.<String, Object>of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.GROUP_ID_CONFIG, "demo-group"
        );

        var options = ReceiverOptions
                .create(consumerConfig)
                .subscription(List.of("order-events"));

        KafkaReceiver
                .create(options)
                .receive()
                .doOnNext(r -> LOG.info("key: {}, value: {}", r.key(), r.value()))
                .subscribe();
    }

}
