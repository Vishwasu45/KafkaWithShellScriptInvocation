package com.vishwas.Customer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vishwas.Customer.dto.EmployeeDto;
import com.vishwas.Customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getList() throws JsonProcessingException {
        var list = customerService.getEmployeeRemote();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
