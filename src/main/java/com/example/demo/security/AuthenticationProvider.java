package com.example.demo.security;

import com.example.demo.model.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@EnableWebSecurity
@RequiredArgsConstructor
@Component
public class AuthenticationProvider implements AuthenticationManager {


    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MyUserDetails user = new MyUserDetails();

        user = (MyUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());
        System.out.println(passwordEncoder.encode(password));


        if (username.equals(user.getUsername()) && passwordEncoder.matches(password, user.getPassword())) {
            Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
            return auth;
        }
        throw new RuntimeException("Invalid username or password");

    }

}

