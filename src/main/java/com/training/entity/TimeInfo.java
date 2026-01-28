package com.training.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;

@Embeddable
public class TimeInfo {
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}