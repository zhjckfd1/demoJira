package com.example.demojira.exceptions;

public class IncorrectStatusChangeException extends MyException{
    public IncorrectStatusChangeException() {
        super("Такое изменение статуса задачи недопустимо");
    }
}
