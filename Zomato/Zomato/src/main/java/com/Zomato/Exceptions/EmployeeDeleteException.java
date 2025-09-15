package com.Zomato.Exceptions;

public class EmployeeDeleteException extends RuntimeException {
    public EmployeeDeleteException(String message) {
        super(message);
    }
}
