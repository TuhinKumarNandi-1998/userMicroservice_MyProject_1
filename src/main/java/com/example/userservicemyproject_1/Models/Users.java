package com.example.userservicemyproject_1.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Getter
@Setter
@Entity
public class Users extends BaseModel {
    private String name;
    private String email;
    private String password;
    private boolean verified;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Roles> roles;
}
