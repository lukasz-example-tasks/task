package com.lukasz.example.api.tor;

import com.lukasz.example.domain.tor.IpAddressException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(IpAddressException.class)
    public void handleConflict(IpAddressException ex) {
        log.warn(ex.getMessage(), ex);
    }
}