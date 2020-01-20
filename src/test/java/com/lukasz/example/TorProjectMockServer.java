package com.lukasz.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.test.web.client.match.MockRestRequestMatchers;

@RequiredArgsConstructor
public class TorProjectMockServer {

    public static final String HOST = "https://localhost:8081";

    @Getter
    private final MockRestServiceServer mockRestServiceServer;

    public ResponseActions expect(ExpectedCount expectedCount, String uri) {
        return mockRestServiceServer.expect(expectedCount, MockRestRequestMatchers.requestTo(HOST + uri));
    }
}