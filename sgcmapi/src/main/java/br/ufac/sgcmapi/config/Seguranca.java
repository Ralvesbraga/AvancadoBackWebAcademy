package br.ufac.sgcmapi.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class Seguranca {
    
    private final PerfilUsuarioService perfilUsuarioService;

    public Seguranca(
        PerfilUsuarioService perfilUsuarioService
    ){
        this.perfilUsuarioService = perfilUsuarioService;
    }
    @Bean
    AuthenticationManager authManager (AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

    @Bean
    UserDetailsService udServicce(){
        return perfilUsuarioService;
    }

    @Bean
    BCryptPasswordEncoder passEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authProvider(){
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(udServicce());
        authProvider.setPasswordEncoder(passEncoder());
        return authProvider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.httpBasic(withDefaults());
        http.cors(withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.authenticationProvider(authProvider());
        http.authorizeHttpRequests(
            authorize -> authorize.requestMatchers("/config/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        );

        return http.build();
    }
}
