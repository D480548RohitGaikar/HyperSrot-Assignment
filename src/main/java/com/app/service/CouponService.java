package com.app.service;

import java.util.List;


import com.app.entities.Coupon;

public interface CouponService {
	
	Coupon addNewCoupon(Coupon coupon);
	
	List<Coupon> getCoupons();

	Coupon getCouponByCode(String code);
}
