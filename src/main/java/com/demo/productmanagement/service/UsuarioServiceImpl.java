package com.demo.productmanagement.service;

import com.demo.productmanagement.model.Usuario;
import com.demo.productmanagement.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public boolean saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario) > 0;
    }

    @Override
    public boolean updateUsuario(Usuario usuario) {
        return usuarioRepository.update(usuario) > 0;
    }

    @Override
    public boolean deleteUsuario(Long id) {
        return usuarioRepository.deleteById(id) > 0;
    }
}
