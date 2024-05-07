package com.example.userservicemyproject_1.Security.Models;

import com.example.userservicemyproject_1.Models.Roles;
import com.example.userservicemyproject_1.Models.Users;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@JsonDeserialize
public class CustomerUserDetails implements UserDetails {
    private Users users;
    private List<CustomGrantedAuthority> authorities;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public CustomerUserDetails() {}

    public CustomerUserDetails(Users users) {
        this.users = users;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.password = users.getPassword();
        this.username = users.getEmail();

        List<CustomGrantedAuthority> authorityList = new ArrayList<>();
        for (Roles roles : users.getRoles()) {
            authorityList.add(new CustomGrantedAuthority(roles));
        }
        this.authorities = authorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorityList = new ArrayList<>();
//        for (Roles roles : users.getRoles()) {
//            authorityList.add(new CustomGrantedAuthority(roles));
//        }
        return authorities;
    }

    @Override
    public String getPassword() {
//        return users.getPassword();
        return password;
    }

    @Override
    public String getUsername() {
//        return users.getEmail();
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
