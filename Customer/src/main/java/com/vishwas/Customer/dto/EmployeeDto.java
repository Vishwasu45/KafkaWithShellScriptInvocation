package com.vishwas.Customer.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private int employeeId;

    private String employeeName;
}
