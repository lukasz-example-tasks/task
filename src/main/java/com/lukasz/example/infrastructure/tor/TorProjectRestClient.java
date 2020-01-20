package com.lukasz.example.infrastructure.tor;

import com.lukasz.example.domain.tor.TorProjectClient;
import com.lukasz.example.application.config.HazelcastConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@CacheConfig(cacheNames = HazelcastConfiguration.ADDRESSES_CACHE_NAME)
class TorProjectRestClient implements TorProjectClient {

    public final String addressesUrl;
    public final RestTemplate restTemplate;

    public TorProjectRestClient(@Value("${tor.exit.addresses.source.url}") String addressesUrl,
                                RestTemplate restTemplate) {
        this.addressesUrl = addressesUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable
    public String exitNodeAddresses() {
        ResponseEntity<String> exchange = restTemplate.exchange(
                addressesUrl,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class);
        return exchange.getBody();
    }
}
