package com.lukasz.example.domain.tor

import spock.lang.Specification
import spock.lang.Unroll

class ExitAddressFacadeTest extends Specification {

    String addresses =
    "ExitNode 0011BD2485AD45D984EC4159C88FC066E5E3300E\n" +
    "Published 2020-01-17 02:23:53\n" +
    "LastStatus 2020-01-17 03:03:13\n" +
    "ExitAddress 162.247.74.201 2020-01-17 03:04:03\n" +
    "ExitNode 0020D8A204A81FC8C8A1D79E2324621705DE4F2F\n" +
    "Published 2020-01-17 05:06:37\n" +
    "LastStatus 2020-01-17 06:02:13\n" +
    "ExitAddress 178.17.170.116 2020-01-17 06:07:29\n" +
    "ExitNode 0077BCBA7244DB3E6A5ED2746E86170066684887\n" +
    "Published 2020-01-17 02:30:42\n" +
    "LastStatus 2020-01-17 03:03:13\n" +
    "ExitAddress 199.249.230.103 2020-01-17 03:15:00"
    ExitAddressRetrieval addressRetrieval = Mock()
    ExitAddressFacade addressFacade = new ExitAddressFacade(addressRetrieval);

    @Unroll
    def "Should recognise exist ip address correctly #ipAddresses"() {
        given:
        addressRetrieval.retrieveAllAddresses() >> addresses

        expect:
        expectedResult == addressFacade.isIPAddressExists(ipAddresses);

        where:
        ipAddresses      | expectedResult
        "162.247.74.2"   | false
        "162.247.74.201" | true
    }

    @Unroll
    def "Throw exception due to use of invalid ip address #ipAddresses"() {
        given:
        addressRetrieval.retrieveAllAddresses() >> addresses
        when:

        addressFacade.isIPAddressExists(ipAddress);

        then:
        thrown(IpAddressException)

        where:
        ipAddress << ["123", "", "   ", "0.123", "123.123.0"]
    }

    def "Verify correct counting of exit addresses"() {
        given:
        addressRetrieval.retrieveAllAddresses() >> addresses
        expect:
        3 == addressFacade.countExitAddresses();
    }
}
