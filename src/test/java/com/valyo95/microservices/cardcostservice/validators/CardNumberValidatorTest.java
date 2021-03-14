package com.valyo95.microservices.cardcostservice.validators;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardNumberValidatorTest {
    private final CardNumberValidator cardNumberValidator = new CardNumberValidator();

    @Test
    public void testInvalid_empty() {
        boolean valid = cardNumberValidator.isValid("", null);
        assertThat(valid).isFalse();
    }

    @Test
    public void testInvalid_containsNonDigit() {
        boolean valid = cardNumberValidator.isValid("123456788765432!", null);
        assertThat(valid).isFalse();
    }

    @Test
    public void testInvalid_containsLessThan16() {
        boolean valid = cardNumberValidator.isValid("123", null);
        assertThat(valid).isFalse();
    }

    @Test
    public void testInvalid_containsMoreThan16() {
        boolean valid = cardNumberValidator.isValid("12312312312312312312314213231231", null);
        assertThat(valid).isFalse();
    }

    @Test
    public void testValid() {
        boolean valid = cardNumberValidator.isValid("1234567887654321", null);
        assertThat(valid).isTrue();
    }
}