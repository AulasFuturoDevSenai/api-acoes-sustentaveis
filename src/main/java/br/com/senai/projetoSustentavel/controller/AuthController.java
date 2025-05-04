package br.com.senai.projetoSustentavel.controller;

import br.com.senai.projetoSustentavel.config.security.JwtService;
import br.com.senai.projetoSustentavel.model.dtos.AuthenticationRequest;
import br.com.senai.projetoSustentavel.model.dtos.AuthenticationResponse;
import br.com.senai.projetoSustentavel.service.ResponsavelDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ResponsavelDetailsService responsavelDetailsService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, ResponsavelDetailsService responsavelDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.responsavelDetailsService = responsavelDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest request) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationManager.authenticate(authToken);

            var userDetails = responsavelDetailsService.loadUserByUsername(request.getUsername()); // Correção aqui
            String token = jwtService.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos.");
        }
    }
}