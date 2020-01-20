package com.lukasz.example.domain.tor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ExitAddressRetrieval {

    private final TorProjectClient torProjectClient;

    String retrieveAllAddresses() {
        return torProjectClient.exitNodeAddresses();
    }
}
