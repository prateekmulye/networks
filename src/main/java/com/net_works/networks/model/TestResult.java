package com.net_works.networks.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

/**
 * Represents the result of a network test execution.
 * This entity stores the details of each test result including the configuration used,
 * timestamp of execution, duration, and success status.
 */

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TestResult {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id; // Unique identifier for the test result

    private Long configId; // Reference to the associated TestConfig
    private Instant timestamp; // Timestamp of when the test was executed
    private long durationMs; // Duration of the test in milliseconds
    private boolean success; // Whether the test was successful or not
}
