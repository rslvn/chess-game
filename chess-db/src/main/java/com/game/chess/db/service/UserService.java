package com.game.chess.db.service;

import com.game.chess.common.exception.NotFoundException;
import com.game.chess.db.model.User;
import com.game.chess.db.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@AllArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class UserService {

    private UserRepository userRepository;


    public User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> NotFoundException.to("User not found by email: %s", email));
    }

    public Optional<User> getOptionalUser(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }


}
