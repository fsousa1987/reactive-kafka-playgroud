package com.francisco.reactive_kafka_playgroud.sec01;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Map;

/*
    goal: to demo a simple Kafka consumer using reactor Kafka

    producer --> Kafka broker <---> consumer
*/
public class Lec01KafkaConsumer {

    static void main() {

        var consumerConfig = Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.GROUP_ID_CONFIG, "demo-group"
        );

        // ReceiverOptions.create();
        // KafkaReceiver.create();

    }

}
