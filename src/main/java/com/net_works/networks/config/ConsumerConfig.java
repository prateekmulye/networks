package com.net_works.networks.config;

import org.springframework.context.annotation.*;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.net_works.networks.model.TestResult;
import java.util.Map;

@Configuration
public class ConsumerConfig {
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TestResult>
            kafkaListenerContainerFactory(ConsumerFactory<String, TestResult> consumerFactory) {
                ConcurrentKafkaListenerContainerFactory<String, TestResult> factory = 
                        new ConcurrentKafkaListenerContainerFactory<>();
                factory.setConsumerFactory(consumerFactory);
                factory.setConcurrency(3); // Set the number of concurrent consumers
                factory.getContainerProperties().setPollTimeout(3000); // Set poll timeout
                return factory;
    }

    @Bean
    public ConsumerFactory<String, TestResult> consumerFactory() {
        JsonDeserializer<TestResult> deserializer = new JsonDeserializer<>(TestResult.class);
        deserializer.addTrustedPackages("*"); // Allow all packages for deserialization;
        return new DefaultKafkaConsumerFactory<>(
            Map.of(
                org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, "newtqork-tester"
            ),
            new org.apache.kafka.common.serialization.StringDeserializer(),
            deserializer
        );
    }
}
