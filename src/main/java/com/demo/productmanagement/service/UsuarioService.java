package com.demo.productmanagement.service;

import com.demo.productmanagement.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    /**
     * Retorna todos los usuarios
     * @return lista de usuarios
     */
    List<Usuario> getAllUsuarios();

    /**
     * Busca un usuario por ID
     * @param id el ID del usuario
     * @return Optional<Usuario> si existe
     */
    Optional<Usuario> getUsuarioById(Long id);

    /**
     * Guarda un nuevo usuario
     * @param usuario datos del usuario
     * @return true si fue guardado
     */
    boolean saveUsuario(Usuario usuario);

    /**
     * Actualiza un usuario existente
     * @param usuario datos del usuario
     * @return true si se actualizó
     */
    boolean updateUsuario(Usuario usuario);

    /**
     * Elimina un usuario por ID
     * @param id el ID del usuario
     * @return true si fue eliminado
     */
    boolean deleteUsuario(Long id);
}
