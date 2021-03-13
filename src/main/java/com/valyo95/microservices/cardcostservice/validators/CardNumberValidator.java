package com.valyo95.microservices.cardcostservice.validators;

import com.valyo95.microservices.cardcostservice.annotations.Pan;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator that check is a given string is a valid card number
 */
public class CardNumberValidator implements ConstraintValidator<Pan, String> {

    @Override
    public boolean isValid(String cardNumber, ConstraintValidatorContext constraintValidatorContext) {
        // For the purposes of the project a valid card number must have exactly 16 digits
        // Check more info: https://www.creditcardinsider.com/learn/anatomy-of-a-credit-card/
        return cardNumber.matches("[0-9]{16}");
    }
}
