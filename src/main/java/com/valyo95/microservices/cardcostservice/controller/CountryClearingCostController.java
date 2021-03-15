package com.valyo95.microservices.cardcostservice.controller;

import com.valyo95.microservices.cardcostservice.dto.CountryClearingCostDTO;
import com.valyo95.microservices.cardcostservice.entity.CountryClearingCost;
import com.valyo95.microservices.cardcostservice.service.CountryClearingCostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/country-cost")
public class CountryClearingCostController {

    private final CountryClearingCostService countryClearingCostService;

    public CountryClearingCostController(CountryClearingCostService countryClearingCostService) {
        this.countryClearingCostService = countryClearingCostService;
    }

    @GetMapping
    public ResponseEntity<List<CountryClearingCost>> findAll() {
        List<CountryClearingCost> countryClearingCosts = countryClearingCostService.findAll();
        return ResponseEntity.ok(countryClearingCosts);
    }

    @PostMapping
    public ResponseEntity<CountryClearingCost> save(@Valid @RequestBody CountryClearingCostDTO countryClearingCost) {
        CountryClearingCost savedCountryClearingCost = countryClearingCostService.saveCountryClearingCost(countryClearingCost);
        return ResponseEntity.ok(savedCountryClearingCost);
    }

    @GetMapping("/{countryCode}")
    public ResponseEntity<CountryClearingCost> findByCountryCode(@PathVariable String countryCode) {
        CountryClearingCost countryClearingCost = countryClearingCostService.findByCountryCode(countryCode);
        return ResponseEntity.ok(countryClearingCost);
    }

    @PutMapping("/{countryCode}")
    public ResponseEntity<CountryClearingCost> update(@PathVariable String countryCode, @Valid @RequestBody CountryClearingCostDTO countryClearingCost) {
        CountryClearingCost updatedCountryClearingCost = countryClearingCostService.updateCountryClearingCost(countryCode, countryClearingCost);
        return ResponseEntity.ok(updatedCountryClearingCost);
    }

    @DeleteMapping("/{countryCode}")
    public ResponseEntity<CountryClearingCost> delete(@PathVariable String countryCode) {
        CountryClearingCost countryClearingCost = countryClearingCostService.deleteCountryClearingCost(countryCode);
        return ResponseEntity.ok(countryClearingCost);
    }

}
