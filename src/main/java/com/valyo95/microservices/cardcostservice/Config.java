package com.valyo95.microservices.cardcostservice;

import com.valyo95.microservices.cardcostservice.entity.CountryClearingCost;
import com.valyo95.microservices.cardcostservice.repository.CountryClearingCostRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * <pre>
     * The simplest way of pre-loading a database table with some entries.
     * It's although not practical cause if you need changes you must re-compile the code.
     *
     * Better solution would be to use <a href="https://flywaydb.org/documentation/getstarted/advanced/java">flyway</a> migration
     * </pre>
     *
     * @param countryClearingCostRepository the Country Clearing Cost Repository
     * @return
     */
    @Bean
    public ApplicationRunner initializeDefaultCountryClearingCosts(CountryClearingCostRepository countryClearingCostRepository) {
        return args -> countryClearingCostRepository.saveAll(Arrays.asList(
                new CountryClearingCost("US", BigDecimal.valueOf(5)),
                new CountryClearingCost("GR", BigDecimal.valueOf(15))
        ));
    }
}
