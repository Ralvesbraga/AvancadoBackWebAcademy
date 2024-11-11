package br.ufac.sgcmapi.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenFilter extends OncePerRequestFilter{

    private final TokenService tokenService;
    private final PerfilUsuarioService usuarioService;

    public TokenFilter(TokenService tokenService,
                        PerfilUsuarioService usuarioService
                        ){
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;

    }

    private String recuperarToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                var token = this.recuperarToken(request);
                if (token != null) {
                    var login = tokenService.validarToken(token);
                    if (login != null) {
                        var usuario = usuarioService.loadUserByUsername(login);
                        var auth = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
                filterChain.doFilter(request, response);
    }
    
}
