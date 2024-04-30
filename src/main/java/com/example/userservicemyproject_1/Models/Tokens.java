package com.example.userservicemyproject_1.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Tokens extends BaseModel {
    private String value;

    @ManyToOne
    private Users users;
    private Date expiryAt;
}
