package com.example.userservicemyproject_1.Repositories;

import com.example.userservicemyproject_1.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users save(Users users);

    Optional<Users> findByEmail(String email);
}
