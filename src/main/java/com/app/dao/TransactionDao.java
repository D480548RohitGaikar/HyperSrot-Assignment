package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Transaction;

public interface TransactionDao extends JpaRepository<Transaction, Long>{

	List<Transaction> findAllByOrderId(Long orderId);

}
