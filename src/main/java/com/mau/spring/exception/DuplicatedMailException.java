package com.mau.spring.exception;

public class DuplicatedMailException extends RuntimeException{

    public DuplicatedMailException(String message){
        super(message);
    }
}
