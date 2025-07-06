package com.net_works.networks.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.net_works.networks.model.TestResult;

/**
 * Configuration class for setting up Kafka producer.
 * This class defines the producer factory and Kafka template used to send TestResult messages.
 */

@Configuration
public class KafkaConfig {
    @Bean
    public ProducerFactory<String, TestResult> producerFactory() {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, TestResult> KafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
