package com.net_works.networks.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.net_works.networks.model.TestConfig;
import com.net_works.networks.repo.TestConfigRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/test-config")
public class TestConfigController {
    private final TestConfigRepository repo;
    
    public TestConfigController(TestConfigRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<TestConfig> list() { 
        return repo.findAll();
    }

    @PostMapping
    public TestConfig create(@Valid @RequestBody TestConfig testConfig) {
        testConfig.setActive(true);
        return repo.save(testConfig);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestConfig> update(
        @PathVariable Long id,
        @Valid @RequestBody TestConfig input) {
            return repo.findById(id)
            .map(cfg -> {
                cfg.setTarget(input.getTarget());
                cfg.setType(input.getType());
                cfg.setFrequencyMs(input.getFrequencyMs());
                cfg.setThresholdMs(input.getThresholdMs());
                cfg.setActive(input.isActive());
                return ResponseEntity.ok(repo.save(cfg));
            })
            .orElse(ResponseEntity.notFound().build());
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}