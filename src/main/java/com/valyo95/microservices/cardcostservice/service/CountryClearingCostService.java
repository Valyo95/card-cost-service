package com.valyo95.microservices.cardcostservice.service;

import com.valyo95.microservices.cardcostservice.entity.CountryClearingCost;
import com.valyo95.microservices.cardcostservice.repository.CountryClearingCostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class CountryClearingCostService {

    @Value("${card-cost.defaultClearingCost}")
    private BigDecimal defaultClearingCost;

    private final CountryClearingCostRepository countryClearingCostRepository;

    public CountryClearingCostService(CountryClearingCostRepository countryClearingCostRepository) {
        this.countryClearingCostRepository = countryClearingCostRepository;
    }

    public CountryClearingCost saveCountryClearingCost(CountryClearingCost countryClearingCost) {
        log.info("Adding new {} in the database.");
        return countryClearingCostRepository.save(countryClearingCost);
    }

    public CountryClearingCost findByCountryCode(String countryCode) {
        return countryClearingCostRepository.findByCountryCodeIgnoreCase(countryCode.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Country clearing cost not found for country code: " + countryCode));
    }

    public List<CountryClearingCost> findAll() {
        return countryClearingCostRepository.findAll();
    }

    public CountryClearingCost updateCountryClearingCost(String countryCode, CountryClearingCost countryClearingCostDetails) {
        CountryClearingCost countryClearingCost = countryClearingCostRepository.findByCountryCodeIgnoreCase(countryCode)
                .orElseThrow(() -> new ResourceNotFoundException("Country clearing cost not found for country code: " + countryCode));

        log.info("Updating CountryClearingCost with countryCode: {}, new values: {}", countryCode, countryClearingCostDetails);

        countryClearingCost.setCost(countryClearingCostDetails.getCost());
        return countryClearingCostRepository.save(countryClearingCost);
    }

    public CountryClearingCost deleteCountryClearingCost(String countryCode) {
        CountryClearingCost countryClearingCost = countryClearingCostRepository.findByCountryCodeIgnoreCase(countryCode)
                .orElseThrow(() -> new ResourceNotFoundException("Country clearing cost not found for country code: " + countryCode));

        log.info("Deleting CountryClearingCost with countryCode: {}", countryCode);
        countryClearingCostRepository.delete(countryClearingCost);
        return countryClearingCost;
    }

    public BigDecimal getDefaultClearingCost() {
        return defaultClearingCost;
    }

    public void setDefaultClearingCost(BigDecimal defaultClearingCost) {
        log.info("Setting the default clearing cost to: {}", defaultClearingCost);
        this.defaultClearingCost = defaultClearingCost;
    }

}
