package com.example.demojira.exceptions;

public class TryingToCreateABondOnYourselfException extends RuntimeException {

    public TryingToCreateABondOnYourselfException() {
        super("id задач совпадают");
    }
}
