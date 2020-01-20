package com.lukasz.example.domain.tor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ExitAddressFacade {

    private final ExitAddressRetrieval exitAddressRetrieval;

    public boolean isIPAddressExists(String ipAddress) {
        log.info("Start verification ip address =  " + ipAddress);
        ExitAddressExist exitAddressExist = ExitAddressExist.builder()
                .exitAddressRetrieval(exitAddressRetrieval)
                .ipAddress(ipAddress)
                .build();
        return exitAddressExist.isExist();
    }

    public int countExitAddresses() {
        ExitAddressCounter exitAddressCounter = ExitAddressCounter.builder()
                .exitAddressRetrieval(exitAddressRetrieval)
                .build();
        return exitAddressCounter.countExitAddresses();
    }
}
