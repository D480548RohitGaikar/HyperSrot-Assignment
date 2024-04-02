package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Coupon;

public interface CouponDao extends JpaRepository<Coupon, Long>{

	Coupon findByCode(String code);

}
