package com.valyo95.microservices.cardcostservice.controller;

import com.valyo95.microservices.cardcostservice.dto.CardNumber;
import com.valyo95.microservices.cardcostservice.dto.binapi.BinLookupResponse;
import com.valyo95.microservices.cardcostservice.entity.CountryClearingCost;
import com.valyo95.microservices.cardcostservice.service.BinApiClient;
import com.valyo95.microservices.cardcostservice.service.CountryClearingCostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/payment-cards-cost")
public class PaymentCardCostController {

    private final CountryClearingCostService countryClearingCostService;
    private final BinApiClient binApiClient;

    public PaymentCardCostController(CountryClearingCostService countryClearingCostService, BinApiClient binApiClient) {
        this.countryClearingCostService = countryClearingCostService;
        this.binApiClient = binApiClient;
    }

    @PostMapping
    public ResponseEntity<CountryClearingCost> getClearingCostForCardNumber(@Valid @RequestBody CardNumber cardNumber) {
        BinLookupResponse binLookupResponse = binApiClient.getBinLookupResponse(getBINFromCardNumber(cardNumber));
        String alpha2Code = binLookupResponse.getCountry().getAlpha2();

        CountryClearingCost countryClearingCost;
        try {
            countryClearingCost = countryClearingCostService.findByCountryCode(alpha2Code);
        } catch (ResourceNotFoundException e) {
            log.info("Country clearing cost for country: `{}` not found. Using default clearing cost.", alpha2Code);
            countryClearingCost = new CountryClearingCost(alpha2Code, countryClearingCostService.getDefaultClearingCost());
        }
        return ResponseEntity.ok(countryClearingCost);
    }

    private String getBINFromCardNumber(CardNumber cardNumber) {
        return cardNumber.getPan().substring(0, 6);
    }

}