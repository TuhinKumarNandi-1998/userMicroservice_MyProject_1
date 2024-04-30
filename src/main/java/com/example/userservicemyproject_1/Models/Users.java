package com.example.userservicemyproject_1.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Users extends BaseModel {
    private String name;
    private String email;
    private String password;
    private boolean verified;

    @ManyToMany
    private List<Roles> roles;
}
