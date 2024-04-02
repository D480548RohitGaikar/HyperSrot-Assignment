package com.app.custom_exceptions;

public class InvalidOrderException extends RuntimeException {
	public InvalidOrderException(String message) {
        super(message);
    }
}
