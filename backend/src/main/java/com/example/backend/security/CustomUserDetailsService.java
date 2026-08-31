package com.example.backend.security;

import com.example.backend.entity.user.UserEntity;
import com.example.backend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Loading user by email: {}", email);

        UserEntity userEntity = userRepo.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("Email not found: {}", email);
                    return new UsernameNotFoundException("Email " + email + " not found");
                });

        Collection<SimpleGrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toCollection(ArrayList::new));

        log.info("User loaded with authorities: {}", authorities);

        return new User(userEntity.getEmail(), userEntity.getId(), authorities);
    }
}