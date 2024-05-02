package com.example.userservicemyproject_1.dtos;

import com.example.userservicemyproject_1.Models.Roles;
import com.example.userservicemyproject_1.Models.Users;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String name;
    private String email;
    private boolean verified;

    @ManyToMany
    private List<Roles> roles;

    public static UserDTO from(Users users) {
        if (users == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setName(users.getName());
        userDTO.setEmail(users.getEmail());
        userDTO.setVerified(users.isVerified());
        userDTO.setRoles(users.getRoles());

        return userDTO;
    }
}
