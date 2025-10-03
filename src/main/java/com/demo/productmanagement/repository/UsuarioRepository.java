package com.demo.productmanagement.repository;

import com.demo.productmanagement.model.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Usuario> rowMapper = (rs, rowNum) -> {
        Usuario u = new Usuario();
        u.setId(rs.getLong("id"));
        u.setNombre(rs.getString("nombre"));
        u.setEmail(rs.getString("email"));
        u.setRol(rs.getString("rol"));
        return u;
    };

    // Listar todos
    public List<Usuario> findAll() {
        String sql = "SELECT * FROM usuarios ORDER BY id DESC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Buscar por ID
    public Optional<Usuario> findById(Long id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
    }

    // Insertar nuevo
    public int save(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, email, rol) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, usuario.getNombre(), usuario.getEmail(), usuario.getRol());
    }

    // Actualizar
    public int update(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, email = ?, rol = ? WHERE id = ?";
        return jdbcTemplate.update(sql, usuario.getNombre(), usuario.getEmail(), usuario.getRol(), usuario.getId());
    }

    // Eliminar
    public int deleteById(Long id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
