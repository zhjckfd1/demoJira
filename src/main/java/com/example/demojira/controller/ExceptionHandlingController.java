package com.example.demojira.controller;

import com.example.demojira.exceptions.EntityAlreadyExistsException;
import com.example.demojira.exceptions.MyEntityNotFoundException;
import com.example.demojira.exceptions.TryingToCreateABondOnYourselfException;
import com.example.demojira.exceptions.IncorrectStatusChangeException;
import com.example.demojira.exceptions.responseBody.BaseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlingController {
    /*
    @ResponseStatus(value= HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler({MyEntityNotFoundException.class})
    public void notFound() {
        // Nothing to do
        //System.out.println("not found");
    }
     */

    /*
    @ResponseBody
    @ResponseStatus(value= HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler({MyEntityNotFoundException.class})
    public BaseError notFound() {
        return new BaseError("entity not found");
    }
     */

    @ResponseBody
    @ResponseStatus(value= HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler({MyEntityNotFoundException.class})
    public BaseError notFound(RuntimeException ex) {
        return new BaseError(ex.getMessage());
    }
    //одно исключение, но message передаем снаружи?


    @ResponseStatus(value= HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public void alreadyFound() {
        // Nothing to do
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler({IncorrectStatusChangeException.class})
    public void incorrectTaskStatusUpdate() {
        // Nothing to do
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler({TryingToCreateABondOnYourselfException.class})
    public void incorrectTasksRelationshipCreate() {
        // Nothing to do
    }

}
