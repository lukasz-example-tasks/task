package com.lukasz.example.domain.tor;

import lombok.Builder;
import lombok.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
class ExitAddressCounter {

    @NonNull
    private final ExitAddressRetrieval exitAddressRetrieval;

    int countExitAddresses() {
        Pattern pattern = Pattern.compile(ExitAddressConst.WORD_BOUNDARY +
                ExitAddressConst.ADDRESS_PREFIX +
                ExitAddressConst.IP_PATTERN +
                ExitAddressConst.WORD_BOUNDARY);
        String addresses = exitAddressRetrieval.retrieveAllAddresses();
        Matcher countMatcher = pattern.matcher(addresses);
        return countExitIpOccurrences(countMatcher);
    }

    private int countExitIpOccurrences(Matcher countMatcher) {
        int count = 0;
        while (countMatcher.find()) {
            count++;
        }
        return count;
    }
}
