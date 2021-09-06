package com.example.demojira.exceptions.responseBody;

import java.time.LocalDateTime;

public class BaseError {
    private final String message;
    private final LocalDateTime localDateTime = LocalDateTime.now();

    public BaseError(){
        this.message = "Unknown error";
    }

    public BaseError(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
