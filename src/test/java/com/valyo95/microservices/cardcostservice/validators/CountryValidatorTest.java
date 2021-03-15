package com.valyo95.microservices.cardcostservice.validators;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CountryValidatorTest {
    private final CountryValidator countryValidator = new CountryValidator();

    @Test
    void testInvalid_empty() {
        boolean valid = countryValidator.isValid("", null);
        assertThat(valid).isFalse();
    }

    @Test
    void testInvalid_invalid() {
        boolean valid = countryValidator.isValid("greece", null);
        assertThat(valid).isFalse();
    }

    @Test
    void testInvalid_nonExisting() {
        boolean valid = countryValidator.isValid("Ellada", null);
        assertThat(valid).isFalse();
    }

    @Test
    void testValid() {
        boolean valid = countryValidator.isValid("GR", null);
        assertThat(valid).isTrue();
    }
}