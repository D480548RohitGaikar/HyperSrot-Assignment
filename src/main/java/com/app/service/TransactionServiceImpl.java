package com.app.service;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.app.dao.TransactionDao;

import com.app.entities.Transaction;


@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private TransactionDao transactionDao;
	
	 public Transaction saveTransaction(Transaction transaction) {
	        return transactionDao.save(transaction);
	    }
	 
	 @Override
	    public List<Transaction> getTransactionsByOrderId(Long orderId) {
	        return transactionDao.findAllByOrderId(orderId);
	    }
}
