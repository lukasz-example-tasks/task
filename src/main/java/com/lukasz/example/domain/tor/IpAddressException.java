package com.lukasz.example.domain.tor;

public class IpAddressException extends RuntimeException {
    public IpAddressException(String message) {
        super(message);
    }

    public static void invalidFormat(String ipAddress){
        String message = String.format("Invalid format of ip address [ipAddress = %s, correct pattern = %s]",
                ipAddress,
                ExitAddressConst.IP_PATTERN);
        throw new IpAddressException(message);
    }
}
