package com.valyo95.microservices.cardcostservice.controller;

import com.valyo95.microservices.cardcostservice.CardCostServiceApplication;
import com.valyo95.microservices.cardcostservice.dto.CardNumber;
import com.valyo95.microservices.cardcostservice.dto.ErrorDetails;
import com.valyo95.microservices.cardcostservice.entity.CountryClearingCost;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

// Clear the application context in each test in order to clear the DB
// This thus makes IT test slower as the application context is reloaded on each test
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = CardCostServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentCardCostControllerIT {

    private final String BG_BIN = "404256";
    private final String GR_BIN = "407534";
    private final String US_BIN = "300027";
    private final String NON_EXISTING_BIN = "003003";

    private final TestRestTemplate restTemplate = new TestRestTemplate("user", "user");

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void testGetClearingCostForCardNumber_GR_BIN() {
        // Given
        // GR clearing cost loaded by default into the DB
        CardNumber cardNumber = new CardNumber(GR_BIN + RandomStringUtils.randomNumeric(10));

        // When
        ResponseEntity<CountryClearingCost> countryClearingCostResponseEntity = restTemplate.postForEntity(getRootUrl() + "/payment-cards-cost", cardNumber, CountryClearingCost.class);

        CountryClearingCost countryClearingCost = countryClearingCostResponseEntity.getBody();

        // Then
        assertThat(countryClearingCost).isNotNull();
        assertThat(countryClearingCost.getCountryCode()).isEqualToIgnoringCase("GR");
        assertThat(countryClearingCost.getCost()).isEqualByComparingTo(BigDecimal.valueOf(15));
    }

    @Test
    void testGetClearingCostForCardNumber_US_BIN() {
        // Given
        // US clearing cost loaded by default into the DB
        CardNumber cardNumber = new CardNumber(US_BIN + RandomStringUtils.randomNumeric(10));

        // When
        ResponseEntity<CountryClearingCost> countryClearingCostResponseEntity = restTemplate.postForEntity(getRootUrl() + "/payment-cards-cost", cardNumber, CountryClearingCost.class);

        CountryClearingCost countryClearingCost = countryClearingCostResponseEntity.getBody();

        // Then
        assertThat(countryClearingCost).isNotNull();
        assertThat(countryClearingCost.getCountryCode()).isEqualToIgnoringCase("US");
        assertThat(countryClearingCost.getCost()).isEqualByComparingTo(BigDecimal.valueOf(5));
    }

    @Test
    void testGetClearingCostForCardNumber_BG_BIN() {
        // Given
        CardNumber cardNumber = new CardNumber(BG_BIN + RandomStringUtils.randomNumeric(10));

        // When
        ResponseEntity<CountryClearingCost> countryClearingCostResponseEntity = restTemplate.postForEntity(getRootUrl() + "/payment-cards-cost", cardNumber, CountryClearingCost.class);

        CountryClearingCost countryClearingCost = countryClearingCostResponseEntity.getBody();

        // Then
        assertThat(countryClearingCost).isNotNull();
        assertThat(countryClearingCost.getCountryCode()).isEqualToIgnoringCase("BG");
        assertThat(countryClearingCost.getCost()).isEqualByComparingTo(BigDecimal.valueOf(10));
    }

    @Test
    void testGetClearingCostForCardNumber_NON_EXISTING_BIN() {
        // Given
        CardNumber cardNumber = new CardNumber(NON_EXISTING_BIN + RandomStringUtils.randomNumeric(10));

        // When
        ResponseEntity<ErrorDetails> countryClearingCostResponseEntity = restTemplate.postForEntity(getRootUrl() + "/payment-cards-cost", cardNumber, ErrorDetails.class);

        ErrorDetails errorDetails = countryClearingCostResponseEntity.getBody();

        // Then
        assertThat(errorDetails).isNotNull();
        assertThat(errorDetails.getError()).contains("not found in BinList API");
    }
}