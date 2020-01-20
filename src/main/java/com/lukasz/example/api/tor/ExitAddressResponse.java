package com.lukasz.example.api.tor;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ExitAddressResponse {

    public ExitAddressResponse(int torExitNodesCount) {
        this.torExitNodesCount = torExitNodesCount;
    }

    int torExitNodesCount;
}
