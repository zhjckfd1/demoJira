package com.example.demojira.exceptions;

//@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException() {
        super("Entity already exists");
    }
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
