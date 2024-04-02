package com.app.custom_exceptions;

public class OrderNotFoundException extends RuntimeException {
	public OrderNotFoundException(String message) {
        super(message);
    }
}
