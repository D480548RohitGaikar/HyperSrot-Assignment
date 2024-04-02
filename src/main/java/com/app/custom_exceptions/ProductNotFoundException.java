package com.app.custom_exceptions;

public class ProductNotFoundException extends RuntimeException{
	public ProductNotFoundException(String message) {
        super(message);
    }
}
