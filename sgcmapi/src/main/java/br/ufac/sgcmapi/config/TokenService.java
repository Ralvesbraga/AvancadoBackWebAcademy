package br.ufac.sgcmapi.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.ufac.sgcmapi.model.Usuario;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    private Instant gerarDataEspiracao(){
        var dateTime = LocalDateTime.now().plusMinutes(1);
        var zoneId = ZoneId.systemDefault();
        var zoneDateTime = dateTime.atZone(zoneId);
        return zoneDateTime.toInstant();
    }

    public String criarToken(Usuario usuario){

        var secret_crypt = Algorithm.HMAC256(secret);
        var token = JWT.create()
                        .withIssuer("SGCM")
                        .withSubject(usuario.getNomeUsuario())
                        .withClaim("nomeCompleto", usuario.getNomeCompleto())
                        .withClaim("papel", usuario.getPapel().toString())
                        .withClaim("dataLimiteRenovacao", LocalDate.now().toString())
                        .withExpiresAt(gerarDataEspiracao())
                        .sign(secret_crypt);
        return token;
    }

    public String validarToken(String token){
        var secret_crypt = Algorithm.HMAC256(secret);
        var tokenValidado = JWT.require(secret_crypt)
                                .withIssuer("SGCM")
                                .build()
                                .verify(token);

        return tokenValidado.getSubject();
    }
}