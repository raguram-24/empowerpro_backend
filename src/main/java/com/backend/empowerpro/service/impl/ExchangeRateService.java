package com.backend.empowerpro.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    @Value("${exchange.api.url}")
    private String apiUrl;

    @Value("${exchange.api.key}")
    private String apiKey;
    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateService.class);


    public double getUsdToLkrRate() {
        String url = String.format("%s?access_key=%s", apiUrl, apiKey);
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // Log the response to ensure the correct structure
        if (response != null) {
            logger.info("Exchange rate API response: " + response);
        }

        if (response != null && response.containsKey("conversion_rates")) {
            Map<String, Double> conversionRates = (Map<String, Double>) response.get("conversion_rates");
            // Check if LKR is available in the response
            if (conversionRates.containsKey("LKR")) {
                double lkrRate = conversionRates.get("LKR");
                logger.info("USD to LKR rate: " + lkrRate);
                return lkrRate;
            } else {
                throw new RuntimeException("LKR rate not found in response");
            }
        }

        throw new RuntimeException("Failed to fetch exchange rates");
    }


}

