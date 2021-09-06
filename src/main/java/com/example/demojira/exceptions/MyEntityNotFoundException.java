package com.example.demojira.exceptions;

public class MyEntityNotFoundException extends MyException {

    public MyEntityNotFoundException() {
        super("Entity is not found");
    }

    public MyEntityNotFoundException(Integer id) {
        super("entity is not found, id = " + id);
    }

    public MyEntityNotFoundException(String message) {
        super(message);
    }

}
