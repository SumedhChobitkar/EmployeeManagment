package com.Zomato.Exceptions;

public class EmployeeNotFoundException extends RuntimeException{
   public EmployeeNotFoundException(String kahipn){
        super(kahipn);
    }

}
