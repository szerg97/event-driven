package com.eventdriven.bookpersistence.service;

import com.eventdriven.common.schema.PublishSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Consumer {

    private final BookPersistenceService bookPersistenceService;

    @KafkaListener(
            topics = {"${spring.kafka.topics.first-topic.name}"},
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(final ConsumerRecord<String, PublishSchema> record) {
        final String key = record.key();
        final PublishSchema value = record.value();
        log.info("Consumed key {} with record: {}", key, value);
        bookPersistenceService.persist(value);
    }
}
