package com.lukasz.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TorProjectMockServerFactory {

    private final RestTemplate restTemplate;

    public TorProjectMockServer create(){
        return new TorProjectMockServer(MockRestServiceServer.bindTo(restTemplate).bufferContent().build());
    }
}
