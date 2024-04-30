package com.example.userservicemyproject_1.Repositories;

import com.example.userservicemyproject_1.Models.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokensRepository extends JpaRepository<Tokens, Long> {
    Tokens save(Tokens tokens);

    Optional<Tokens> findByValueAndDeleted(String value, boolean deleted);
}
