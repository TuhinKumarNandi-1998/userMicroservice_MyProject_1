package com.example.userservicemyproject_1.Security.Services;

import com.example.userservicemyproject_1.Models.Users;
import com.example.userservicemyproject_1.Repositories.UserRepository;
import com.example.userservicemyproject_1.Security.Models.CustomerUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = userRepository.findByEmail(username);

        if(optionalUsers.isEmpty()) {
            throw new UsernameNotFoundException("user with email "+username+" not found in the DB.");
        }
        UserDetails userDetails = new CustomerUserDetails(optionalUsers.get());
        return userDetails;
    }
}
