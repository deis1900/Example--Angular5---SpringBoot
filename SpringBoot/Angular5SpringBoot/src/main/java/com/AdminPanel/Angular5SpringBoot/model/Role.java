package com.AdminPanel.Angular5SpringBoot.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, SAD;

    @Override
    public String getAuthority() {
        return name();
    }
}