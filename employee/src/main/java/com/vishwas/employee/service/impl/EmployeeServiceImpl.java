package com.vishwas.employee.service.impl;

import com.vishwas.employee.dto.EmployeeDto;
import com.vishwas.employee.repository.EmployeeRepository;
import com.vishwas.employee.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    @CircuitBreaker(name = "EmployeeService", fallbackMethod = "employeeFallBack")
    public List<EmployeeDto> getEmployees() {
        var s = employeeRepository.findAll();
        return s.stream()
                .map(a -> EmployeeDto.builder()
                        .employeeId(a.getEmployeeId())
                        .employeeName(a.getEmployeeName())
                        .build())
                .collect(Collectors.toList());
    }

    public List<EmployeeDto> employeeFallBack(CallNotPermittedException ex) throws Exception {
        throw new Exception("Employee Service is down");
    }
}
