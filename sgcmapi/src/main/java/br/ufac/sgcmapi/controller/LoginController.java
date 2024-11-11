package br.ufac.sgcmapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.sgcmapi.config.PerfilUsuario;
import br.ufac.sgcmapi.config.TokenService;
import br.ufac.sgcmapi.model.Usuario;
import br.ufac.sgcmapi.service.UsuarioService;

@RestController
public class LoginController {

    private final AuthenticationManager authManager;
    private final UsuarioService usuarioService;
    private final TokenService tokenService;

    
    public LoginController
    (AuthenticationManager authManager,
    UsuarioService usuarioService, 
    TokenService tokenService){
        this.authManager = authManager;
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;

    }

    @PostMapping("/autenticacao")
    public ResponseEntity<String> autenticar(@RequestBody Usuario usuario){
        
        var loginToken = new UsernamePasswordAuthenticationToken(usuario.getNomeUsuario(), usuario.getSenha());
        var auth = authManager.authenticate(loginToken);
        PerfilUsuario principal = (PerfilUsuario) auth.getPrincipal();

        var usuarioAutenticado = usuarioService.getByNomeUsuario(principal.getUsername());
        var token = tokenService.criarToken(usuarioAutenticado);

        return ResponseEntity.ok(token);

    }
}
