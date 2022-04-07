package com.mau.spring.exception.handler;

import com.mau.spring.exception.DuplicatedMailException;
import com.mau.spring.exception.ErrorResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.MappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {IllegalArgumentException.class, MappingException.class, ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Please review your input and try again.", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DuplicatedMailException.class})
    protected ResponseEntity<Object> handleDuplicatedEmail(
            RuntimeException ex, WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("The email is already used", details);
        return new ResponseEntity(error, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
