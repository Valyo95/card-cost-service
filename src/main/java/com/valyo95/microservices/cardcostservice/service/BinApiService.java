package com.valyo95.microservices.cardcostservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BinApiService {

    private final RestTemplate restTemplate;

    public BinApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


}
