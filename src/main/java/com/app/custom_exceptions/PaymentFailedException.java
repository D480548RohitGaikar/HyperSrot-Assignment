package com.app.custom_exceptions;

public class PaymentFailedException extends RuntimeException {
	public PaymentFailedException(String message) {
        super(message);
    }
}
