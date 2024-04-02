package com.app.dto;

public class PaymentResponse {
    private Long userId;
    private Long orderId;
    private Long transactionId;
    private String status;
    private String description;

    
    public PaymentResponse() {
    }

    public PaymentResponse(Long userId, Long orderId, Long transactionId, String status, String description) {
        this.userId = userId;
        this.orderId = orderId;
        this.transactionId = transactionId;
        this.status = status;
        this.description = description;
    }

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

