package com.net_works.networks.service;

import com.net_works.networks.repo.TestConfigRepository;

import java.net.InetAddress;
import java.time.Instant;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.net_works.networks.model.TestConfig;
import com.net_works.networks.model.TestResult;
import com.net_works.networks.repo.*;

public class TestExecutionService {
    private final TestConfigRepository configRepo;
    private final KafkaTemplate<String, TestResult> kafka;
    private final RestTemplate rest = new RestTemplate();

    public TestExecutionService(TestConfigRepository configRepo, KafkaTemplate<String, TestResult> kafka) {
        this.configRepo = configRepo;
        this.kafka = kafka;
    }

    @Scheduled(fixedDelay = 5_000)
    public void runTests(){
        for (TestConfig cfg : configRepo.findByActiveTrue()) {
            TestResult result = execute(cfg);
            kafka.send("test-results", String.valueOf(result.getId()), result);
        }
    }

    private TestResult execute(TestConfig cfg) {
        long start = System.currentTimeMillis();
        boolean success = false;
        try {
            if ("ICMP".equalsIgnoreCase(cfg.getType())) {
                success = InetAddress.getByName(cfg.getTarget()).isReachable((int) cfg.getThresholdMs());
            }else {
                rest.getForEntity(cfg.getTarget(), String.class);
                success = true;
            }
        } catch (Exception e) {
            success = false;
        }
        long duration = System.currentTimeMillis() - start;
        return TestResult.builder()
                .configId(cfg.getId())
                .timestamp(Instant.now())
                .durationMs(duration)
                .success(success)
                .build();
    }
}
