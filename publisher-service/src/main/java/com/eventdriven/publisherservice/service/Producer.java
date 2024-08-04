package com.eventdriven.publisherservice.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.eventdriven.common.schema.PublishSchema;

@Slf4j
@Service
public class Producer {

    @Value("${spring.kafka.topics.first-topic.name}")
    private String topic;
    private final KafkaTemplate<String, PublishSchema> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, PublishSchema> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(final PublishSchema schema){
        final ProducerRecord<String, PublishSchema> record = new ProducerRecord<>(topic, schema.getIsbn().toString(), schema);
        kafkaTemplate.send(record);
        log.info("Sent record: {}", record);
    }
}
