package com.mkumar.blogpost.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class BlogAPIException extends RuntimeException{

    private HttpStatus status;

    public String message;

}
