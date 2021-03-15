package com.valyo95.microservices.cardcostservice.validators;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class CountryValidatorTest {
    private final CountryValidator countryValidator = new CountryValidator();

    @ParameterizedTest
    @ValueSource(strings = {"", "greece!", "123", "Ellada"})
    void testInvalid(String countryCode) {
        boolean valid = countryValidator.isValid(countryCode, null);
        assertThat(valid).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"GR", "BG", "US", "AD"})
    void testValid(String countryCode) {
        boolean valid = countryValidator.isValid(countryCode, null);
        assertThat(valid).isTrue();
    }
}