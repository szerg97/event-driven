package com.eventdriven.authorpersistence.service;

import com.eventdriven.common.schema.AuthorSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Producer {

    @Value("${spring.kafka.topics.second-topic.name}")
    private String topic;
    private final KafkaTemplate<String, AuthorSchema> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, AuthorSchema> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(final AuthorSchema schema){
        final ProducerRecord<String, AuthorSchema> record = new ProducerRecord<>(topic, schema.getId().toString(), schema);
        kafkaTemplate.send(record);
        log.info("Sent record: {}", record);
    }
}
