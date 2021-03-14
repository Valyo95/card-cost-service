package com.valyo95.microservices.cardcostservice.service;

import com.valyo95.microservices.cardcostservice.dto.binapi.BinLookupResponse;
import com.valyo95.microservices.cardcostservice.exceptions.BinApiClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class BinApiClient {

    private final String binlistURL;

    private final RestTemplate restTemplate;

    public BinApiClient(@Value("${binlist.api.lookup.url}") String binlistURL,
                        RestTemplate restTemplate) {
        this.binlistURL = binlistURL;
        this.restTemplate = restTemplate;
    }

    /**
     * The method makes a call to the Bin API to retrieve a BinLookup response.
     * In order to minimize the calls a caching mechanism is added to cache each
     * of the bin responses
     * @param bin
     * @return
     */
    @Cacheable("BIN")
    public BinLookupResponse getBinLookupResponse(String bin) {
        try {
            return restTemplate.getForObject(binlistURL + "/" + bin, BinLookupResponse.class);
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
    }

    /**
     * Evicting the BIN cache
     */
    @Scheduled(fixedRateString = "${binlist.api.clearBinCacheInterval}")
    @CacheEvict(value = "BIN", allEntries = true)
    public void clearCache() {
        log.info("Clearing BIN cache");
    }
}
