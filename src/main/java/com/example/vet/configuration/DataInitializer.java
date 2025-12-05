package com.example.vet.configuration;

import com.example.vet.model.Role;
import com.example.vet.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;

    @Bean
    public CommandLineRunner initRoles() {
        return args -> {
            if (roleRepository.findByAuthority("ROLE_USER") == null) {
                roleRepository.save(new Role("ROLE_USER"));
            }
            if (roleRepository.findByAuthority("ROLE_ADMIN") == null) {
                roleRepository.save(new Role("ROLE_ADMIN"));
            }
        };
    }
}
