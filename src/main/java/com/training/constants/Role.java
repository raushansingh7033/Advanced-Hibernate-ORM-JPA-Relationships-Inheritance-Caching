package com.training.constants;

public enum Role {
	ROLE_STUDENT,
	ROLE_INSTRUCTOR,
	ROLE_ADMIN;

    public String authority() {
        return name();
    }

    public String withoutPrefix() {
        return name().replace("ROLE_", "");
    }
}
