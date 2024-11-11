package br.ufac.sgcmapi.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Seguranca {

    private final PerfilUsuarioService perfilUsuarioService;
    private final TokenFilter tokenFilter;

    public Seguranca(
            PerfilUsuarioService perfilUsuarioService,
            TokenFilter tokenFilter) {
        this.perfilUsuarioService = perfilUsuarioService;
        this.tokenFilter = tokenFilter;
    }

    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    UserDetailsService udService() {
        return perfilUsuarioService;
    }

    @Bean
    BCryptPasswordEncoder passEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authProvider() {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(udService());
        authProvider.setPasswordEncoder(passEncoder());
        return authProvider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // http.httpBasic(withDefaults());
        http.cors(withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.authenticationProvider(authProvider());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(
            authorize -> authorize
                .requestMatchers(HttpMethod.POST,"/autenticacao").permitAll()
                .requestMatchers("/config/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        );

        http.addFilterBefore(tokenFilter,
         UsernamePasswordAuthenticationFilter.class);
        
        return http.build();

    }
    
}
