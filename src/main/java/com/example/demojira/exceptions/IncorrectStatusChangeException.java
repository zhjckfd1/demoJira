package com.example.demojira.exceptions;

public class IncorrectStatusChangeException extends RuntimeException{
    public IncorrectStatusChangeException() {
        super("Такое изменение статуса задачи недопустимо");
    }
}
