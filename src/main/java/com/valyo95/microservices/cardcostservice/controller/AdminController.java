package com.valyo95.microservices.cardcostservice.controller;

import com.valyo95.microservices.cardcostservice.dto.ClearingCost;
import com.valyo95.microservices.cardcostservice.service.CountryClearingCostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;


@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final CountryClearingCostService countryClearingCostService;

    public AdminController(CountryClearingCostService countryClearingCostService) {
        this.countryClearingCostService = countryClearingCostService;
    }

    @PostMapping("/defaultClearingCost")
    public ResponseEntity<String> updateDefaultClearingCost(@Valid @Positive @RequestBody ClearingCost cost) {
        countryClearingCostService.setDefaultClearingCost(cost.getCost());
        return ResponseEntity.ok("Default clearing cost set to: " + cost.getCost());
    }
}