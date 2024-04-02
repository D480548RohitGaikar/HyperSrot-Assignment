package com.app.service;

import java.util.List;

import com.app.entities.Transaction;

public interface TransactionService {
	Transaction saveTransaction(Transaction transaction);

	List<Transaction> getTransactionsByOrderId(Long orderId);
}
