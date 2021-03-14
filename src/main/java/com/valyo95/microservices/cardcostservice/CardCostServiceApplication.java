package com.valyo95.microservices.cardcostservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching
@SpringBootApplication
public class CardCostServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardCostServiceApplication.class, args);
    }

}
