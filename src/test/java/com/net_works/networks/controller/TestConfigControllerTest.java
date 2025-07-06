package com.net_works.networks.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.mockito.Mock;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.net_works.networks.model.TestConfig;
import com.net_works.networks.repo.TestConfigRepository;

@WebMvcTest
@Import(TestConfigController.class)
public class TestConfigControllerTest {
    @Autowired MockMvc mockMvc;
    @Mock TestConfigRepository repo;
    
    @Test
    void listReturnsAll() throws Exception {
        given(repo.findAll()).willReturn(List.of(
            new TestConfig(1L, "http://example.com", "HTTP", 1000, 200, true)
        ));
        mockMvc.perform(get("/api/test-configs"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].target").value("http://example.com"));
    }
}
