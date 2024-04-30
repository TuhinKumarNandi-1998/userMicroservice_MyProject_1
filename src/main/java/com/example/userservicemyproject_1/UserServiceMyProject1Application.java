package com.example.userservicemyproject_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UserServiceMyProject1Application {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceMyProject1Application.class, args);
    }

}
