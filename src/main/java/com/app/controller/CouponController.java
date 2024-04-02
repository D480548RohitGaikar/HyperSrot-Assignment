package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.app.entities.Coupon;

import com.app.service.CouponService;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;
        
    @PostMapping
    public Coupon addNewCoupon(@RequestBody Coupon coupon) {
    	return couponService.addNewCoupon(coupon);
    }

    @GetMapping
    public List<Coupon> getCoupons() {
        return couponService.getCoupons();
    }
}
