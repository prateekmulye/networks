package com.net_works.networks.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TestResult {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id; // Unique identifier for the test result

    private Long testConfigId; // Reference to the associated TestConfig
    private Instant timestamp; // Timestamp of when the test was executed
    private long durationMs; // Duration of the test in milliseconds
    private boolean success; // Whether the test was successful or not
}
