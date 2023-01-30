package com.vishwas.Customer.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerServiceException extends RuntimeException {

    private String message;
}
