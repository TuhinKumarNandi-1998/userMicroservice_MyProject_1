package com.example.userservicemyproject_1.Security.Models;

import com.example.userservicemyproject_1.Models.Roles;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {
    private Roles roles;
    private String authority;

    public CustomGrantedAuthority() {}

    public CustomGrantedAuthority(Roles roles) {
        this.roles = roles;
        this.authority = roles.getName();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
