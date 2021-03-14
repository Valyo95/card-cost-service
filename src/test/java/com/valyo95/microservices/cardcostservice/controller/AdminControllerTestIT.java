package com.valyo95.microservices.cardcostservice.controller;

import com.valyo95.microservices.cardcostservice.CardCostServiceApplication;
import com.valyo95.microservices.cardcostservice.dto.ClearingCost;
import com.valyo95.microservices.cardcostservice.dto.ErrorDetails;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = CardCostServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminControllerTestIT {

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void updateDefaultClearingCost_nonAdmin() {
        // Given
        TestRestTemplate restTemplate = new TestRestTemplate("user", "user");

        // When
        Map<String, Object> response = restTemplate.postForObject(getRootUrl() + "/admin/" + "defaultClearingCost", 777, Map.class);

        // Then
        assertThat(response.size()).isGreaterThan(0);
        assertThat(response.get("status")).isEqualTo(403);
        assertThat(response.get("error")).isEqualTo("Forbidden");
    }

    @Test
    void updateDefaultClearingCost_Admin() {
        // Given
        TestRestTemplate restTemplate = new TestRestTemplate("admin", "admin");
        ClearingCost clearingCost = new ClearingCost(BigDecimal.valueOf(777));

        // When
        ResponseEntity<String> response = restTemplate.postForEntity(getRootUrl() + "/admin/" + "defaultClearingCost", clearingCost, String.class);

        // Then
        assertThat(response.getBody()).isEqualTo("Default clearing cost set to: " + 777);
    }

    @Test
    void updateDefaultClearingCost_nonValidCost() {
        // Given
        TestRestTemplate restTemplate = new TestRestTemplate("admin", "admin");
        ClearingCost clearingCost = new ClearingCost(BigDecimal.valueOf(-123));

        // When
        ResponseEntity<ErrorDetails> response = restTemplate.postForEntity(getRootUrl() + "/admin/" + "defaultClearingCost", clearingCost, ErrorDetails.class);

        // Then
        ErrorDetails error = response.getBody();
        assertThat(error).isNotNull();
        assertThat(error.getError()).contains("must be greater than or equal to 0");
    }
}