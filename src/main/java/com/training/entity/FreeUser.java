package com.training.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "FREE")
public class FreeUser extends User {
	private double freeCredits;

	public double getFreeCredits() {
		return freeCredits;
	}

	public void setFreeCredits(double freeCredits) {
		this.freeCredits = freeCredits;
	}
	
}
