package com.net_works.networks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.net_works.networks.model.TestResult;

/**
 * Repository interface for managing TestResult entities.
 * Provides methods to perform CRUD operations on test results.
 */

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    // This repository will handle CRUD operations for TestResult entities
    // Additional custom query methods can be defined here if needed
}
