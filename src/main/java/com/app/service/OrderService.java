package com.app.service;


import java.util.List;


import com.app.dto.OrderDetailsWithTransactions;
import com.app.entities.Order;
import com.app.entities.Transaction;

public interface OrderService {

	Order placeOrder(Long userId, int qty, String coupon, Long productId);

	Transaction makePayment(Long userId, Long orderId, double amount);

	List<Order> getUserOrders(Long userId);

	OrderDetailsWithTransactions getOrderDetailsWithTransactions(Long userId, Long orderId);
	
	
	
}
