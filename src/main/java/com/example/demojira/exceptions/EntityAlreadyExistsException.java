package com.example.demojira.exceptions;

public class EntityAlreadyExistsException extends MyException {

    public EntityAlreadyExistsException() {
        super("Entity already exists");
    }
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
