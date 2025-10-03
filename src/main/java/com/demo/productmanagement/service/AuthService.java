package com.demo.productmanagement.service;

import com.demo.productmanagement.model.AuthUser;

import java.util.Optional;

public interface AuthService {

    /**
     * Verifica las credenciales de login
     * @param username El nombre de usuario
     * @param contrasena La contraseña
     * @return Optional<AuthUser> si es válido y tiene rol admin, vacío en caso contrario
     */
    Optional<AuthUser> login(String username, String contrasena);
}
