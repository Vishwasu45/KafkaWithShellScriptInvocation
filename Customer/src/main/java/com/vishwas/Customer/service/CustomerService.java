package com.vishwas.Customer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vishwas.Customer.dto.EmployeeDto;

import java.util.List;

public interface CustomerService {

    List<EmployeeDto> getEmployeeRemote() throws JsonProcessingException;

}
