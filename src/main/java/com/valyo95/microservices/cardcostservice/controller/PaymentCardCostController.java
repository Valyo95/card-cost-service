package com.valyo95.microservices.cardcostservice.controller;

import com.valyo95.microservices.cardcostservice.dto.CardNumber;
import com.valyo95.microservices.cardcostservice.entity.CountryClearingCost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/payment-cards-cost")
@Slf4j
public class PaymentCardCostController {

    @PostMapping
    public ResponseEntity<CountryClearingCost> save(@Valid @RequestBody CardNumber cardNumber) {
        log.info("Looking for cost for: {}", cardNumber.getPan());
        //CountryClearingCost savedCountryClearingCost = countryClearingCostService.saveCountryClearingCost(countryClearingCost);
        return ResponseEntity.ok(null);
    }

}