package com.francisco.reactive_kafka_playgroud.sec13;

import reactor.kafka.receiver.ReceiverRecord;

public class RecordProcessingException extends RuntimeException {

    private final ReceiverRecord<?, ?> record;

    public RecordProcessingException(ReceiverRecord<?, ?> record, Exception e) {
        super(e);
        this.record = record;
    }

    public ReceiverRecord<?, ?> getRecord() {
        return record;
    }

}
