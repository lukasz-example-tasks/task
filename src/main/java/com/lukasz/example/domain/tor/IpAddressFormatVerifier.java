package com.lukasz.example.domain.tor;

class IpAddressFormatVerifier {

    static void verify(String ipAddress) {
        if (ipAddress == null || !ipAddress.matches(ExitAddressConst.IP_PATTERN)) {
            IpAddressException.invalidFormat(ipAddress);
        }
    }
}