package com.vishwas.Customer.Exception;

import com.vishwas.Customer.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler({CustomerServiceException.class})
    public ResponseEntity<String> handleException(CustomerServiceException e) {
        var response = new Response(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
        return new ResponseEntity<>(response.getMessage(), response.getStatus());
    }
}
