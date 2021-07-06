package com.example.demojira.controller;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.support.SQLErrorCodes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandlingController {
    @ResponseStatus(value= HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(EntityNotFoundException.class)
    public void notFound() {
        // Nothing to do
    }


    /*
    //https://stackoverflow.com/questions/6221951/how-to-catch-a-specific-sqlexception-error
    //SQL Error: 2291, SQLState: 23000   (как обработать только данную ошибку?)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(SQLException.class)         //???
    public void notFoundSql() {
    }*/
}
