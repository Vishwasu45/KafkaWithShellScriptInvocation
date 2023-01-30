package com.vishwas.employee.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {

    private int employeeId;

    private String employeeName;
}
