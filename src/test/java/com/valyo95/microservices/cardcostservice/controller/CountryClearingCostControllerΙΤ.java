package com.valyo95.microservices.cardcostservice.controller;

import com.valyo95.microservices.cardcostservice.CardCostServiceApplication;
import com.valyo95.microservices.cardcostservice.dto.ErrorDetails;
import com.valyo95.microservices.cardcostservice.entity.CountryClearingCost;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Clear the application context in each test in order to clear the DB
// This thus makes IT test slower as the application context is reloaded on each test
@SpringBootTest(classes = CardCostServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CountryClearingCostControllerΙΤ {

    private final TestRestTemplate restTemplate = new TestRestTemplate("user", "user");

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void testGet_GR() {
        // Given
        // GR clearing cost loaded by default into the DB
        String countryCode = "gr";

        // When
        CountryClearingCost countryClearingCost = restTemplate.getForObject(getRootUrl() + "/country-cost/" + countryCode, CountryClearingCost.class);

        // Then
        assertThat(countryClearingCost).isNotNull();
        assertThat(countryClearingCost.getCountryCode()).isEqualToIgnoringCase(countryCode);
        assertThat(countryClearingCost.getCost()).isEqualByComparingTo(BigDecimal.valueOf(15));
    }

    @Test
    void testGet_undefined() {
        // Given
        String countryCode = "undefined";

        // When
        ErrorDetails error = restTemplate.getForObject(getRootUrl() + "/country-cost/" + countryCode, ErrorDetails.class);

        // Then
        assertThat(error).isNotNull();
        assertThat(error.getError()).contains("Country clearing cost not found for country code");
    }

    @Test
    void testGetAll() {
        // Given
        String countryCode = "undefined";

        // When
        List<CountryClearingCost> error = restTemplate.getForObject(getRootUrl() + "/country-cost", List.class);

        // Then
        assertThat(error).hasSize(2);
    }

    @Test
    void testCreate_BG() {
        // Given
        String countryCode = "BG";
        CountryClearingCost countryClearingCost = new CountryClearingCost(countryCode, BigDecimal.valueOf(30));

        // When
        ResponseEntity<CountryClearingCost> countryClearingCostResponseEntity = restTemplate.postForEntity(getRootUrl() + "/country-cost", countryClearingCost, CountryClearingCost.class);

        // Then
        assertThat(countryClearingCostResponseEntity).isNotNull();

        CountryClearingCost body = countryClearingCostResponseEntity.getBody();
        assertThat(body.getCountryCode()).isEqualTo("BG");
        assertThat(body.getCost()).isEqualByComparingTo(BigDecimal.valueOf(30));

        // Delete record
        restTemplate.delete(getRootUrl() + "/country-cost/" + countryCode);
    }

    @Test
    void testCreateAndUpdate_BG() {
        // Given
        String countryCode = "BG";
        CountryClearingCost countryClearingCost = new CountryClearingCost(countryCode, BigDecimal.valueOf(30));
        restTemplate.postForEntity(getRootUrl() + "/country-cost", countryClearingCost, CountryClearingCost.class);

        // When
        countryClearingCost = new CountryClearingCost(countryCode, BigDecimal.valueOf(777));
        restTemplate.put(getRootUrl() + "/country-cost/" + countryCode, countryClearingCost);

        CountryClearingCost newCountryClearingCost = restTemplate.getForObject(getRootUrl() + "/country-cost/" + countryCode, CountryClearingCost.class);

        // Then
        assertThat(newCountryClearingCost.getCountryCode()).isEqualTo(countryCode);
        assertThat(newCountryClearingCost.getCost()).isEqualByComparingTo(BigDecimal.valueOf(777));

        // Delete record
        restTemplate.delete(getRootUrl() + "/country-cost/" + countryCode);
    }

    @Test
    void testUpdate_NonExisting() {
        // Given
        String countryCode = "AF";
        CountryClearingCost countryClearingCost = new CountryClearingCost(countryCode, BigDecimal.valueOf(30));

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        HttpEntity httpEntity = new HttpEntity<Object>(countryClearingCost, headers);

        // When
        countryClearingCost = new CountryClearingCost(countryCode, BigDecimal.valueOf(777));

        ResponseEntity<ErrorDetails> errorDetailsResponseEntity = restTemplate.exchange(getRootUrl() + "/country-cost/" + countryCode, HttpMethod.PUT, httpEntity, ErrorDetails.class);
        ErrorDetails error = errorDetailsResponseEntity.getBody();

        // Then
        assertThat(error).isNotNull();
        assertThat(error.getError()).contains("Country clearing cost not found for country code");
    }

    @Test
    void testCreateAndDelete_BG() {
        // Given
        String countryCode = "BG";
        CountryClearingCost countryClearingCost = new CountryClearingCost(countryCode, BigDecimal.valueOf(30));

        // When
        ResponseEntity<CountryClearingCost> countryClearingCostResponseEntity = restTemplate.postForEntity(getRootUrl() + "/country-cost", countryClearingCost, CountryClearingCost.class);
        CountryClearingCost body = countryClearingCostResponseEntity.getBody();
        restTemplate.delete(getRootUrl() + "/country-cost/" + body.getCountryCode());

        ErrorDetails error = restTemplate.getForObject(getRootUrl() + "/country-cost/" + countryCode, ErrorDetails.class);

        // Then
        assertThat(error).isNotNull();
        assertThat(error.getError()).contains("Country clearing cost not found for country code: " + countryCode);
    }

    @Test
    void testDelete_NonExistent() {
        // Given
        String countryCode = "AF";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        HttpEntity httpEntity = new HttpEntity<>(null, headers);
        
        // When
        ResponseEntity<ErrorDetails> errorDetailsResponseEntity = restTemplate.exchange(getRootUrl() + "/country-cost/" + countryCode, HttpMethod.DELETE, httpEntity, ErrorDetails.class);

        ErrorDetails error = errorDetailsResponseEntity.getBody();

        // Then
        assertThat(error).isNotNull();
        assertThat(error.getError()).contains("Country clearing cost not found for country code");
    }
}