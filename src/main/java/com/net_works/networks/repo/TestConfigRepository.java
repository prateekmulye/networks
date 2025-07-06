package com.net_works.networks.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.net_works.networks.model.TestConfig;

/**
 * Repository interface for managing TestConfig entities.
 * Provides methods to perform CRUD operations and custom queries on test configurations.
 */

public interface TestConfigRepository extends JpaRepository<TestConfig, Long> {
    
   List<TestConfig> findByActiveTrue(); // Method to find active test configurations
}
