package com.accountingg.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {

    ROLE_USER,

    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return toString();
    }
}
