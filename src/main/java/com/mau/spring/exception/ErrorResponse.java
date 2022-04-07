package com.mau.spring.exception;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse
{


    //General error message about nature of error
    private final String message;

    //Specific errors in API request processing
    private final List<String> details;

    //Getter and setters

}