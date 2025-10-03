package com.demo.productmanagement.repository;

import com.demo.productmanagement.model.AuthUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AuthUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public AuthUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<AuthUser> rowMapper = (rs, rowNum) -> {
        AuthUser user = new AuthUser();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setContrasena(rs.getString("contrasena"));
        user.setRol(rs.getString("rol"));
        return user;
    };

    // Validar login: solo acepta admin
    public Optional<AuthUser> login(String username, String contrasena) {
        String sql = "SELECT * FROM auth_users WHERE username = ? AND contrasena = ? AND rol = 'admin'";
        return jdbcTemplate.query(sql, rowMapper, username, contrasena).stream().findFirst();
    }
}
