package com.valyo95.microservices.cardcostservice.validators;

import com.valyo95.microservices.cardcostservice.annotations.Country;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;
import java.util.Set;

/**
 * Validator that checks if a given country code is valid
 */
public class CountryValidator implements ConstraintValidator<Country, String> {

    /**
     * A set of all the ISO ALPHA 2 country codes retrieved from the {@link Locale#getISOCountries()}
     */
    private final Set<String> countryCodes = Set.of(Locale.getISOCountries());

    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {
        return countryCodes.contains(code.toUpperCase());
    }

}
