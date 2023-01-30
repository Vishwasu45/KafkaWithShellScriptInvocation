package com.vishwas.employee.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Employee {

    @Column(name = "employee_id")
    @Id
    private int employeeId;
    @Column(name = "employee_name")
    private String employeeName;
}
