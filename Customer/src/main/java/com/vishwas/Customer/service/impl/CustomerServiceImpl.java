package com.vishwas.Customer.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vishwas.Customer.Exception.CustomerServiceException;
import com.vishwas.Customer.dto.EmployeeDto;
import com.vishwas.Customer.service.CustomerService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final RestTemplate restTemplate;

    private final ObjectMapper mapper;

    private final KafkaTemplate<String, Object> template;

    @Override
    @CircuitBreaker(name = "EmployeeService", fallbackMethod = "getEmployeeFallBack")
    public List<EmployeeDto> getEmployeeRemote() throws JsonProcessingException {
        var a = restTemplate.getForObject("/employees", String.class);
        var res = mapper.readValue(a, EmployeeDto[].class);
        return Arrays.asList(res);
    }

    public List<EmployeeDto> getEmployeeFallBack(CallNotPermittedException ex) {
        publishMessage();
        throw new CustomerServiceException("Employee Service is down");
    }

    public String publishMessage() {
        template.send("vishwasTopic", "Employee is down");
        return "Data published";
    }
}
