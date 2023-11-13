package com.tasks.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotAuthourized extends RuntimeException{
    public String message;

    public NotAuthourized(String message){
        super(message);
        this.message=message;
    }
}
