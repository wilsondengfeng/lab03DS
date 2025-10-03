package com.demo.productmanagement.controller;

import com.demo.productmanagement.model.AuthUser;
import com.demo.productmanagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Mostrar formulario de login
    @GetMapping({"/", "/login"})
    public String showLoginForm(Model model) {
        model.addAttribute("authUser", new AuthUser());
        return "auth/login"; // plantilla en templates/auth/login.html
    }

    // Procesar login
    @PostMapping("/login")
    public String login(@ModelAttribute AuthUser authUser, HttpSession session, Model model) {
        Optional<AuthUser> user = authService.login(authUser.getUsername(), authUser.getContrasena());

        if (user.isPresent()) {
            session.setAttribute("adminLogged", true);
            session.setAttribute("adminName", user.get().getUsername());
            return "redirect:/usuarios"; // redirige al CRUD
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "auth/login";
        }
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
