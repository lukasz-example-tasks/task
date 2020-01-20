package com.lukasz.example.api

import com.lukasz.example.BaseE2E
import com.lukasz.example.api.tor.ExitAddressController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.client.ExpectedCount
import org.springframework.test.web.client.response.MockRestResponseCreators
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [ExitAddressController])
class ExitAddressControllerTest extends BaseE2E {

    @Autowired
    protected MockMvc mockMvc

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

    def "Verify correct recognise count of exit address after invoke status endpoint"() {
        given:
        torProjectMockServer.expect(ExpectedCount.once(), "/exit-addresses")
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK).body(addresses));

        when:
        def results = mockMvc.perform(get('/addresses/status')
                .header("X-API-VERSION", 1)
                .contentType(MediaType.APPLICATION_JSON))

        then:
        results.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print())

        and:
        results.andExpect(jsonPath('$.tor_exit_nodes_count').value(3))
    }

    @Unroll
    def "Verify return correct status #expectedStatus while verification of exists #ipAddress in  exit addresses"() {
        given:
        torProjectMockServer.expect(ExpectedCount.once(), "/exit-addresses")
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK).body(addresses));

        when:
        def results = mockMvc.perform(get('/addresses/' + ipAddress)
                .header("X-API-VERSION", 1)
                .contentType(MediaType.APPLICATION_JSON))

        then:
        results.andExpect(expectedStatus).andDo(MockMvcResultHandlers.print())

        where:
        ipAddress        | expectedStatus
        "162.247.74.201" | status().isOk()
        "162.247.74"     | status().isUnprocessableEntity()
        "162.247.74.234" | status().isNotFound()
    }
}