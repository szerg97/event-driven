package com.eventdriven.publisherservice.service;

import com.eventdriven.common.schema.PublishSchema;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProducerTest {

    @Mock private KafkaTemplate<String, PublishSchema> kafkaTemplate;
    @InjectMocks private Producer underTest;
    @Captor
    private ArgumentCaptor<ProducerRecord<String, PublishSchema>> recordCaptor;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(underTest, "topic", "com.eventdriven.publish.topic.v1");
    }

    @Test
    void testSend() {
        PublishSchema publishSchema = PublishSchema.newBuilder()
                .setIsbn("isbn")
                .setTitle("title")
                .setAuthorName("John Doe")
                .setAuthorAge(23).build();

        underTest.send(publishSchema);

        verify(kafkaTemplate).send(recordCaptor.capture());

        ProducerRecord<String, PublishSchema> expected = new ProducerRecord<>("com.eventdriven.publish.topic.v1", publishSchema.getIsbn().toString(), publishSchema);
        ProducerRecord<String, PublishSchema> actual = recordCaptor.getValue();

        assertEquals(expected.value().toString(), actual.value().toString());
    }
}