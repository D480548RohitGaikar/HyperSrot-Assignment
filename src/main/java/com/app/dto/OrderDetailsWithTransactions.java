package com.app.dto;

import java.time.LocalDate;
import java.util.List;

import com.app.entities.Transaction;

public class OrderDetailsWithTransactions {
    private Long orderId;
    private double amount;
    private LocalDate date;
    private String coupon;
    private List<Transaction> transactions;

    public OrderDetailsWithTransactions(Long orderId, double amount, LocalDate date, String coupon, List<Transaction> transactions) {
        this.orderId = orderId;
        this.amount = amount;
        this.date = date;
        this.coupon = coupon;
        this.transactions = transactions;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}

