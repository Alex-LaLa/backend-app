package com.univo.backend_app.services;

import com.univo.backend_app.models.Usuario;
import com.univo.backend_app.repositories.UsuarioRepository;
import com.univo.backend_app.security.JwtService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public AuthService(
            UsuarioRepository usuarioRepository,
            JwtService jwtService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    public String login(String email, String password) {

        Usuario usuario = usuarioRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        if (!usuario.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return jwtService.generateToken(usuario.getEmail());

    }

}