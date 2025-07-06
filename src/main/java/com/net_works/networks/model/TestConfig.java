package com.net_works.networks.model;

import jakarta.persistence.*;
import lombok.*;    

/**
 * Entity representing a network test configuration.
 * This class is used to store the details of each test configuration in the database.
 */

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TestConfig {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String target; // URL or IP
    private String type; // Type of test (e.g., ICMP, HTTP, etc.)
    private long frequencyMs; // Frequency of the test in milliseconds
    private long thresholdMs; // Threshold for alerting in milliseconds
    private boolean active; // Whether the test is active or not
}
