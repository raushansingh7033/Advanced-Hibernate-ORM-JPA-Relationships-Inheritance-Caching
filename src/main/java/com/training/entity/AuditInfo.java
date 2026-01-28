package com.training.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class AuditInfo {
	private String createdBy;
	@Embedded
	private TimeInfo timeInfo;
}