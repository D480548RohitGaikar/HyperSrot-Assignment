package com.app.dto;

public class OrderDTO {
    private Long orderId;
    private double amount;
    private String couponCode;

    public OrderDTO(Long orderId, double amount, String couponCode) {
        this.orderId = orderId;
        this.amount = amount;
        this.couponCode = couponCode;
    }

    // Getters and setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}

