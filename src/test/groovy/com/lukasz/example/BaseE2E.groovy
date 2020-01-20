package com.lukasz.example


import com.lukasz.example.infrastructure.config.MockTestConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.context.annotation.Import
import spock.lang.Specification

@Import(MockTestConfiguration)
@AutoConfigureMockMvc
class BaseE2E extends Specification {

    @Autowired
    TorProjectMockServerFactory torProjectMockServerFactory;

    TorProjectMockServer torProjectMockServer;

    def setup() {
        torProjectMockServer = torProjectMockServerFactory.create();
    }
}
