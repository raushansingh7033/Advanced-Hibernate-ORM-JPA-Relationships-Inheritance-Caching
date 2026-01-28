package com.training.entity;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "PAID")
public class PremiumUser extends User{
	private LocalDateTime purchasedAt;
	private LocalDateTime expiringAt;
	public LocalDateTime getPurchasedAt() {
		return purchasedAt;
	}
	public void setPurchasedAt(LocalDateTime purchasedAt) {
		this.purchasedAt = purchasedAt;
	}
	public LocalDateTime getExpiringAt() {
		return expiringAt;
	}
	public void setExpiringAt(LocalDateTime expiringAt) {
		this.expiringAt = expiringAt;
	}
	
}
