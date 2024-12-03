package com.backend.empowerpro.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import java.util.Map;

@Service
public class ExchangeRateService {

    @Value("${exchange.api.url}")
    private String apiUrl;

    @Value("${exchange.api.key}")
    private String apiKey;

    public double getUsdToLkrRate() {
        String url = String.format("%s?access_key=%s", apiUrl, apiKey);

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response != null && response.containsKey("rates")) {
            Map<String, Double> rates = (Map<String, Double>) response.get("rates");
            return rates.getOrDefault("LKR", 0.0);
        }

        throw new RuntimeException("Failed to fetch exchange rates");
    }
}

