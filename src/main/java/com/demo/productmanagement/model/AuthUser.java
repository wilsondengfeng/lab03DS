package com.demo.productmanagement.model;

public class AuthUser {
    private Long id;
    private String username;
    private String contrasena;
    private String rol;

    // Constructor vacío
    public AuthUser() {
    }

    // Constructor con parámetros
    public AuthUser(Long id, String username, String contrasena, String rol) {
        this.id = id;
        this.username = username;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
