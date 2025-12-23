package com.francisco.reactive_kafka_playgroud.sec13;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.ReceiverRecord;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;
import reactor.util.retry.Retry;

public class ReactiveDeadLetterTopicProducer<K, V> {

    private static final Logger LOG = LoggerFactory.getLogger(ReactiveDeadLetterTopicProducer.class);

    private final KafkaSender<K, V> sender;
    private final Retry retrySpec;

    public ReactiveDeadLetterTopicProducer(KafkaSender<K, V> sender, Retry retrySpec) {
        this.sender = sender;
        this.retrySpec = retrySpec;
    }

    public Mono<SenderResult<K>> produce(ReceiverRecord<K, V> record) {
        var sr = toSenderRecord(record);
        return this.sender.send(Mono.just(sr)).next();
    }

    private SenderRecord<K, V, K> toSenderRecord(ReceiverRecord<K, V> record) {
        var pr = new ProducerRecord<>(
                record.topic() + "-dlt",
                record.key(),
                record.value()
        );
        return SenderRecord.create(pr, pr.key());
    }

}
