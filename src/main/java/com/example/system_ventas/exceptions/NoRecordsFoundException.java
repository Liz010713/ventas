package com.example.system_ventas.exceptions;

public class NoRecordsFoundException extends RuntimeException {
    public NoRecordsFoundException(String message) {
        super(message);
    }
}