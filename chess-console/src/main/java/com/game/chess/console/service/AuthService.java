package com.game.chess.console.service;

import com.game.chess.common.exception.AlreadyExistException;
import com.game.chess.console.domain.jwt.CustomUserDetails;
import com.game.chess.console.security.JwtTokenProvider;
import com.game.chess.db.model.User;
import com.game.chess.db.service.UserService;
import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class AuthService {

    private static final String ERROR_MESSAGE_USER_NULL = "User can not be null";

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    private UserService userService;

    public String login(User user) {
        // we used checkArgument instead of checkNotNull because we want an IllegalArgumentException
        Preconditions.checkArgument(user != null, ERROR_MESSAGE_USER_NULL);
        // validate user exist or no
        userService.getUser(user.getEmail());

        Authentication authentication = authenticate(user);

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        LOG.info("{} user logged in!", customUserDetails.getUsername());

        return jwtTokenProvider.generateToken(customUserDetails);
    }

    public void register(User user) {
        userService.getOptionalUser(user.getEmail())
                .ifPresent(existUser -> {
                    throw AlreadyExistException.to("User already exist by email: %s", user.getEmail());
                });

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.save(user);
    }

    /**
     * Authenticate user and log them in given a loginRequest
     */
    private Authentication authenticate(User user) {
        // we used checkArgument instead of checkNotNull because we want an IllegalArgumentException
        Preconditions.checkArgument(user != null, ERROR_MESSAGE_USER_NULL);
//        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }
}
