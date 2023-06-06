package com.f5.travel.api.controlers.error_handler;


import com.f5.travel.util.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Todos los codigos [400...499] es un error de "Bad request"
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestController {


    @ExceptionHandler(IdNotFoundException.class)
    public String handleIdNotFound(IdNotFoundException exception){
        return exception.getMessage();
    }

}