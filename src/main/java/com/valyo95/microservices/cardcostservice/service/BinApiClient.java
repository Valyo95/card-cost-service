package com.valyo95.microservices.cardcostservice.service;

import com.valyo95.microservices.cardcostservice.dto.CardNumber;
import com.valyo95.microservices.cardcostservice.dto.binapi.BinLookupResponse;
import com.valyo95.microservices.cardcostservice.exceptions.BinApiClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class BinApiClient {

    @Value("${binlist.api.lookup.url}")
    private String binlistURL;

    private final RestTemplate restTemplate;

    public BinApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BinLookupResponse getBinLookupResponse(CardNumber cardNumber) throws BinApiClientException {
        String bin = getBINFromCardNumber(cardNumber);
        BinLookupResponse binLookupResponse = null;
        try {
            binLookupResponse = restTemplate.getForObject(binlistURL + "/" + bin, BinLookupResponse.class);
        } catch (HttpClientErrorException e) {
            String errorMessage;
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                errorMessage = "BIN: `" + bin + "` not found in BinList API";
            } else {
                errorMessage = "BinList Server Error";
            }
            log.error(errorMessage);
            throw new BinApiClientException(errorMessage, e);
        }
        return binLookupResponse;
    }

    private String getBINFromCardNumber(CardNumber cardNumber) {
        return cardNumber.getPan().substring(0, 6);
    }
    

}
