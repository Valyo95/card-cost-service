package com.valyo95.microservices.cardcostservice.validators;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class CardNumberValidatorTest {
    private final CardNumberValidator cardNumberValidator = new CardNumberValidator();

    @ParameterizedTest
    @ValueSource(strings = {"", "123456788765432!", "123", "12312312312312312312314213231231"})
    void testInvalid(String cardNumber) {
        boolean valid = cardNumberValidator.isValid(cardNumber, null);
        assertThat(valid).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234567887654321", "1234123412341234", "1234567890123456"})
    void testValid(String cardNumber) {
        boolean valid = cardNumberValidator.isValid(cardNumber, null);
        assertThat(valid).isTrue();
    }
}