package com.lukasz.example.api.tor;

import com.lukasz.example.api.schared.ApiParameterConst;
import com.lukasz.example.domain.tor.ExitAddressFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class ExitAddressController {

    private final ExitAddressFacade exitAddressFacade;

    @GetMapping(value = "/{ipAddress}", headers = ApiParameterConst.X_API_VERSION_HEADER_NAME + "1")
    public ResponseEntity<Void> getExitAddresses(@PathVariable("ipAddress") String ipAddress) {
        if (exitAddressFacade.isIPAddressExists(ipAddress)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/status", headers = ApiParameterConst.X_API_VERSION_HEADER_NAME + "1")
    public ResponseEntity<ExitAddressResponse> countExitAddresses() {
        int count = exitAddressFacade.countExitAddresses();
        return ResponseEntity.ok(new ExitAddressResponse(count));
    }
}
