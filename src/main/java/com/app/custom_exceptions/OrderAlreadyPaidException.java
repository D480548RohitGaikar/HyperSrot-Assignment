package com.app.custom_exceptions;

public class OrderAlreadyPaidException extends RuntimeException{
	public OrderAlreadyPaidException(String message) {
        super(message);
    }
}
