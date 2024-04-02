package com.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDate;


@Entity
@Table(name = "coupon")
public class Coupon {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private LocalDate expiryDate;

    // Constructors, getters, and setters
    public Coupon() {}
    
   
    public Coupon(Long id, String code, LocalDate expiryDate) {
		this.id = id;
		this.code = code;
		this.expiryDate = expiryDate;
	}

	// Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }


	@Override
	public String toString() {
		return "Coupon [id=" + id + ", code=" + code + ", expiryDate=" + expiryDate + "]";
	}
    
}
