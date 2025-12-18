package com.coiffeur.rdv.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 🔑 Filtre JWT qui va lire le token Authorization: Bearer ...
    private final JwtAuthFilter jwtAuthFilter;

    // Injection du filtre JWT par Spring
    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    // 🔐 Encoder de mot de passe (login / register)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🔐 Configuration principale Spring Security
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 🌍 Autorise les requêtes cross-origin (Angular, Postman)
                .cors(cors -> {})

                // ❌ CSRF inutile pour une API REST stateless
                .csrf(csrf -> csrf.disable())

                // 🚫 Pas de session HTTP (JWT uniquement)
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 🔓 Règles d'accès
                .authorizeHttpRequests(auth -> auth
                        // Routes publiques (login / register)
                        .requestMatchers("/auth/**").permitAll()

                        // Toutes les autres routes nécessitent un token valide
                        .anyRequest().authenticated()
                )

                // 🧠 Ajout du filtre JWT AVANT le filtre d'authentification Spring
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                // ❌ Désactive login form & basic auth (API only)
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(form -> form.disable())

                // ✅ Build final de la chaîne de sécurité
                .build();
    }
}
