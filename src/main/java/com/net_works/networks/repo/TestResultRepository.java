package com.net_works.networks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.net_works.networks.model.TestResult;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    // This repository will handle CRUD operations for TestResult entities
    // Additional custom query methods can be defined here if needed
}
