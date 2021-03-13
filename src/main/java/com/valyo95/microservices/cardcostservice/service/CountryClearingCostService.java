package com.valyo95.microservices.cardcostservice.service;

import com.valyo95.microservices.cardcostservice.entity.CountryClearingCost;
import com.valyo95.microservices.cardcostservice.repository.CountryClearingCostRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryClearingCostService {

    private final CountryClearingCostRepository countryClearingCostRepository;

    public CountryClearingCostService(CountryClearingCostRepository countryClearingCostRepository) {
        this.countryClearingCostRepository = countryClearingCostRepository;
    }

    public CountryClearingCost saveCountryClearingCost(CountryClearingCost countryClearingCost) {
        return countryClearingCostRepository.save(countryClearingCost);
    }

    public CountryClearingCost findByCountryCode(String countryCode) {
        return countryClearingCostRepository.findById(countryCode)
                .orElseThrow(() -> new ResourceNotFoundException("Country clearing cost not found for country code: " + countryCode));
    }

    public List<CountryClearingCost> findAll() {
        return countryClearingCostRepository.findAll();
    }

    public CountryClearingCost updateCountryClearingCost(String countryCode, CountryClearingCost countryClearingCostDetails) {
        CountryClearingCost countryClearingCost = countryClearingCostRepository.findById(countryCode)
                .orElseThrow(() -> new ResourceNotFoundException("Country clearing cost not found for country code: " + countryCode));
        countryClearingCost.setCost(countryClearingCostDetails.getCost());
        return countryClearingCostRepository.save(countryClearingCost);
    }

    public CountryClearingCost deleteCountryClearingCost(String countryCode) {
        CountryClearingCost countryClearingCost = countryClearingCostRepository.findById(countryCode)
                .orElseThrow(() -> new ResourceNotFoundException("Country clearing cost not found for country code: " + countryCode));
        countryClearingCostRepository.delete(countryClearingCost);
        return countryClearingCost;
    }
}
