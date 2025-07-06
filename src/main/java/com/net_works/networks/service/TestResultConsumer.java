package com.net_works.networks.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.net_works.networks.model.TestResult;
import com.net_works.networks.repo.TestResultRepository;

/**
 * Service to consume test results from Kafka and save them to the database.
 * This service listens to the "test-results" topic and saves each received TestResult
 * to the TestResultRepository.
 */

@Service
public class TestResultConsumer {
    private final TestResultRepository repo;
    public TestResultConsumer(TestResultRepository repo) {
        this.repo = repo;
    }

    @KafkaListener(topics = "test-results", containerFactory = "kafkaListenerContainerFactory")
    public void consume(TestResult result) {
        repo.save(result);
    }
}
