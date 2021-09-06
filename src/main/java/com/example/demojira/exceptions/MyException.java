package com.example.demojira.exceptions;

public class MyException extends RuntimeException{
    public MyException() {
        super("Unknown exception");
    }
    public MyException(String message) {
        super(message);
    }
}
