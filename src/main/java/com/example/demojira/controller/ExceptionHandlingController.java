package com.example.demojira.controller;

import com.example.demojira.exceptions.EntityAlreadyExistsException;
import com.example.demojira.exceptions.TryingToCreateABondOnYourselfException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandlingController {
    @ResponseStatus(value= HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(EntityNotFoundException.class)
    public void notFound() {
        // Nothing to do
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler({EntityAlreadyExistsException.class, TryingToCreateABondOnYourselfException.class})
    public void alreadyFound() {
        // Nothing to do
    }
}
