package com.app.dto;

public class OrderDetailsDTO {
    private Long orderId;
    private double amount;
    private String coupon;
    private String status;

    // Constructors, getters, and setters

    public OrderDetailsDTO(Long orderId, double amount, String coupon, String status) {
        this.orderId = orderId;
        this.amount = amount;
        this.coupon = coupon;
        this.status = status;
    }

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

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

