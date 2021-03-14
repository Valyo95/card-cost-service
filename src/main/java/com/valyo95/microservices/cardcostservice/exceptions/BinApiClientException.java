package com.valyo95.microservices.cardcostservice.exceptions;

import com.valyo95.microservices.cardcostservice.dto.CardNumber;

/**
 * Custom Exception used whenever 
 * the {@link com.valyo95.microservices.cardcostservice.service.BinApiClient#getBinLookupResponse(CardNumber)}
 * fails to get a response
 */
public class BinApiClientException extends RuntimeException {
    public BinApiClientException(String message) {
        super(message);
    }

    public BinApiClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
