package com.f5.travel.util.exceptions;

public class IdNotFoundException extends RuntimeException{
    private static final String ERROR_MSG = "Record no exist in %s";

    public IdNotFoundException(String tableName) {
        super(String.format(ERROR_MSG, tableName));
    }
}