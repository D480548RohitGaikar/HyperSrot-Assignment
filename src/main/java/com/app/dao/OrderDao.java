package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Order;

public interface OrderDao extends JpaRepository<Order, Long>{

	List<Order> findByUser_Id(Long userId);

	Order findByUser_IdAndId(Long userId, Long orderId);

}
