package com.demo.productmanagement.service;

import com.demo.productmanagement.model.AuthUser;
import com.demo.productmanagement.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthUserRepository authUserRepository;

    @Autowired
    public AuthServiceImpl(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public Optional<AuthUser> login(String username, String contrasena) {
        return authUserRepository.login(username, contrasena);
    }
}
