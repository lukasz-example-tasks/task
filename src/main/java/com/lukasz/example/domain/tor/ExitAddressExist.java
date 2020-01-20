package com.lukasz.example.domain.tor;

import lombok.Builder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
@Slf4j
class ExitAddressExist {

    @NonNull
    private final ExitAddressRetrieval exitAddressRetrieval;

    @NonNull
    private final String ipAddress;

    boolean isExist() {
        IpAddressFormatVerifier.verify(ipAddress);
        String addresses = exitAddressRetrieval.retrieveAllAddresses();
        Pattern pattern = createPattern();
        Matcher addressMatcher = pattern.matcher(addresses);
        boolean result = addressMatcher.find();
        log.info("Result of IP verification is [IP = {}, result = {}]", ipAddress, result);
        return result;
    }

    private Pattern createPattern() {
        return Pattern.compile(ExitAddressConst.WORD_BOUNDARY +
                ExitAddressConst.ADDRESS_PREFIX +
                ipAddress +
                ExitAddressConst.WORD_BOUNDARY);
    }
}