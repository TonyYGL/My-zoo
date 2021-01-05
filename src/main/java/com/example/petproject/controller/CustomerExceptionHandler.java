package com.example.petproject.controller;

import com.example.petproject.bean.CustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler({CustomerException.class})
    public ResponseEntity<Map<String, Object>> handle(CustomerException customerException) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("errorCode", customerException.getErrorCode());
        errorMap.put("errorMessage", customerException.getErroMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        System.out.println("!!!!!!!");
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("errorCode", HttpStatus.BAD_REQUEST.value());
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(objectError -> objectError.getDefaultMessage()
                        .concat("(").concat(((FieldError)objectError).getField())
                        .concat(")"))
                .collect(Collectors.joining("&"));

        errorMap.put("errorMessage", errorMessage);
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
