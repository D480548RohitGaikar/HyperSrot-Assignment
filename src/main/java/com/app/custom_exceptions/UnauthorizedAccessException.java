package com.app.custom_exceptions;

public class UnauthorizedAccessException extends RuntimeException {
	public UnauthorizedAccessException(String message) {
        super(message);
    }
}
