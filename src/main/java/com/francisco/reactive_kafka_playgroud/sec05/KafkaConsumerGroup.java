package com.francisco.reactive_kafka_playgroud.sec05;

public class KafkaConsumerGroup {

    private static class Consumer1 {
        static void main() {
            KafkaConsumer.start("1");
        }
    }

    private static class Consumer2 {
        static void main() {
            KafkaConsumer.start("2");
        }
    }

    private static class Consumer3 {
        static void main() {
            KafkaConsumer.start("3");
        }
    }

}
