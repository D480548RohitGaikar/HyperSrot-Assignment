package com.app.custom_exceptions;

public class BankPaymentFailedException extends RuntimeException {
	public BankPaymentFailedException(String message) {
        super(message);
    }
}
