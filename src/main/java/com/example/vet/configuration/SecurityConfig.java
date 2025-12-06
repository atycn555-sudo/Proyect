package com.example.vet.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.vet.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // ✅ Rutas públicas (Swagger, Actuator, etc.)
    private static final String[] WHITE_LIST_URL = {
            "/index.html",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/public/**",
            "/error",
            "/actuator/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(req -> req
                // Permitir CORS preflight
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // Endpoints públicos
                .requestMatchers(WHITE_LIST_URL).permitAll()
                .requestMatchers(HttpMethod.GET, "/").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll() // 👈 registro libre

                // USER + ADMIN pueden consultar y crear clientes, mascotas, historial médico
                .requestMatchers(HttpMethod.POST, "/api/clients/**", "/api/pets/**", "/api/medical-history/**")
                .hasAnyRole("CLIENT", "ADMIN")
                .requestMatchers(HttpMethod.GET,
                        "/api/products/**",
                        "/api/services/**",
                        "/api/veterinarians/**",
                        "/api/shifts/**",
                        "/api/clients/**",
                        "/api/pets/**",
                        "/api/species/**",
                        "/api/vaccines/**",
                        "/api/medical-history/**")
                .hasAnyRole("CLIENT", "ADMIN")

                // SOLO ADMIN puede crear/editar/eliminar especies, vacunas, veterinarios, productos, proveedores
                .requestMatchers(HttpMethod.POST,
                        "/api/species/**",
                        "/api/vaccines/**",
                        "/api/veterinarians/**",
                        "/api/products/**",
                        "/api/shifts/**",
                        "/api/suppliers/**")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")

                // Default: todo lo demás requiere autenticación
                .anyRequest().authenticated()
            )
            // Autenticación básica
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // ✅ PasswordEncoder único
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ AuthenticationProvider usando tu CustomUserDetailsService
    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService,
                                                         PasswordEncoder encoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }

    // ✅ Configuración CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
