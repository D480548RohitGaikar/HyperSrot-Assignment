package com.app.custom_exceptions;

public class InvalidQuantityException extends RuntimeException{
	public InvalidQuantityException(String message) {
        super(message);
    }
}
