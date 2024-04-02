package com.app.service;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CouponDao;

import com.app.entities.Coupon;

@Service
@Transactional
public class CouponServiceImpl implements CouponService{
	
	@Autowired
	private CouponDao couponDao;
	
	@Override
	public List<Coupon> getCoupons() {
		return couponDao.findAll();
	}

	@Override
	public Coupon addNewCoupon(Coupon coupon) {
		return couponDao.save(coupon);
	}

	@Override
    public Coupon getCouponByCode(String code) {
        return couponDao.findByCode(code);
    }
}
