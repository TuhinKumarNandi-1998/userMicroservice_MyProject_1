package com.example.userservicemyproject_1.Services;

import com.example.userservicemyproject_1.Exceptions.InvalidTokenException;
import com.example.userservicemyproject_1.Exceptions.UserNotFoundException;
import com.example.userservicemyproject_1.Exceptions.WrongPasswordException;
import com.example.userservicemyproject_1.Models.Tokens;
import com.example.userservicemyproject_1.Models.Users;
import com.example.userservicemyproject_1.Repositories.TokensRepository;
import com.example.userservicemyproject_1.Repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokensRepository tokensRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       TokensRepository tokensRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokensRepository = tokensRepository;
    }

    public Users signUp(String email,
                        String password,
                        String name) {

        Users users = new Users();
        users.setName(name);
        users.setEmail(email);
        users.setPassword(bCryptPasswordEncoder.encode(password));

        Users users1 = userRepository.save(users);

        return users1;
    }

    public Tokens login(String email, String password) throws UserNotFoundException, WrongPasswordException {
        Optional<Users> optionalUsers = userRepository.findByEmail(email);
        if (optionalUsers.isEmpty()) {
            throw new UserNotFoundException("User with email "+email+" not found.");
        }
        Users users = optionalUsers.get();

        if (!bCryptPasswordEncoder.matches(password, users.getPassword())) {
            throw new WrongPasswordException("Entered password is wrong, Please re-enter correct password.");
        }

        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plus(30, ChronoUnit.DAYS);
        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Tokens tokens = new Tokens();
        tokens.setUsers(users);
        tokens.setExpiryAt(expiryDate);
        tokens.setValue(RandomStringUtils.randomAlphabetic(128));

        return tokensRepository.save(tokens);
    }

    public void logout(String tokenValue) throws InvalidTokenException {
        Optional<Tokens> optionalTokens = tokensRepository.findByValueAndDeleted(tokenValue, false);

        if (optionalTokens.isEmpty()) {
            throw new InvalidTokenException("Token with value "+tokenValue+" is either deleted or expired");
        }

        Tokens tokens = optionalTokens.get();
        tokens.setDeleted(true);

        tokensRepository.save(tokens);
    }

    public Users validateToken(String tokenValue) throws InvalidTokenException {
        Optional<Tokens> optionalTokens = tokensRepository.findByValueAndDeletedAndExpiryAtGreaterThan(tokenValue, false, new Date());

        if (optionalTokens.isEmpty()) {
            throw new InvalidTokenException("Token with value "+tokenValue+" is either expired or not found in DB.");
        }

        return optionalTokens.get().getUsers();
    }
}
