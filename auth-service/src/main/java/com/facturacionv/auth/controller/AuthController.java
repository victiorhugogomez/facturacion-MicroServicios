package com.authservice.controller;

import com.authservice.model.Usuario;
import com.authservice.security.JwtUtil;
import com.authservice.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String username, @RequestParam String password) {
        Usuario usuario = usuarioService.registrarUsuario(username, password);
        return ResponseEntity.ok("Usuario registrado con éxito: " + usuario.getUsuario());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        Optional<Usuario> usuario = usuarioService.buscarPorUsername(username);

        if (usuario.isPresent() && usuarioService.validarPassword(password, usuario.get().getPassword())) {
            String token = JwtUtil.generateToken(username);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}
