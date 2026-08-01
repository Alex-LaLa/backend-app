package com.univo.backend_app.controllers;

import com.univo.backend_app.dto.LoginRequest;
import com.univo.backend_app.dto.LoginResponse;
import com.univo.backend_app.services.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){

        String token = authService.login(
                request.getEmail(),
                request.getPassword()
        );

        return new LoginResponse(token);

    }

}