package com.demo.productmanagement.controller;

import com.demo.productmanagement.model.Usuario;
import com.demo.productmanagement.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Verifica si el admin está logueado
    private boolean checkAdmin(HttpSession session) {
        Object flag = session.getAttribute("adminLogged");
        return (flag instanceof Boolean) && (Boolean) flag;
    }

    // Listar usuarios
    @GetMapping
    public String listUsuarios(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!checkAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión como admin");
            return "redirect:/login";
        }
        model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        return "usuarios/list";
    }

    // Formulario para nuevo usuario
    @GetMapping("/new")
    public String showNewUsuarioForm(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!checkAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión como admin");
            return "redirect:/login";
        }
        model.addAttribute("usuario", new Usuario());
        return "usuarios/form";
    }

    // Guardar usuario
    @PostMapping
    public String saveUsuario(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!checkAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión como admin");
            return "redirect:/login";
        }
        boolean saved = usuarioService.saveUsuario(usuario);
        if (saved) {
            redirectAttributes.addFlashAttribute("message", "Usuario guardado con éxito");
        } else {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el usuario");
        }
        return "redirect:/usuarios";
    }

    // Formulario para editar usuario
    @GetMapping("/edit/{id}")
    public String showEditUsuarioForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!checkAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión como admin");
            return "redirect:/login";
        }
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);

        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            return "usuarios/form";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
            return "redirect:/usuarios";
        }
    }

    // Actualizar usuario
    @PostMapping("/update")
    public String updateUsuario(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!checkAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión como admin");
            return "redirect:/login";
        }
        boolean updated = usuarioService.updateUsuario(usuario);

        if (updated) {
            redirectAttributes.addFlashAttribute("message", "Usuario actualizado con éxito");
        } else {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el usuario");
        }

        return "redirect:/usuarios";
    }

    // Ver usuario
    @GetMapping("/view/{id}")
    public String viewUsuario(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!checkAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión como admin");
            return "redirect:/login";
        }
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);

        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            return "usuarios/view";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
            return "redirect:/usuarios";
        }
    }

    // Eliminar usuario
    @GetMapping("/delete/{id}")
    public String deleteUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!checkAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión como admin");
            return "redirect:/login";
        }
        boolean deleted = usuarioService.deleteUsuario(id);

        if (deleted) {
            redirectAttributes.addFlashAttribute("message", "Usuario eliminado con éxito");
        } else {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el usuario");
        }

        return "redirect:/usuarios";
    }
}
