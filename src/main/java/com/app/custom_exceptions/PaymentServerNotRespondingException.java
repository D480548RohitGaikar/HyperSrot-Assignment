package com.app.custom_exceptions;

public class PaymentServerNotRespondingException extends RuntimeException {
	public PaymentServerNotRespondingException(String message) {
        super(message);
    }
}
