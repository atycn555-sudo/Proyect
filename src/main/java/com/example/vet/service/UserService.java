package com.example.vet.service;

import com.example.vet.dto.UserRequest;
import com.example.vet.dto.UserResponse;
import com.example.vet.mapper.UserMapper;
import com.example.vet.model.Role;
import com.example.vet.model.User;
import com.example.vet.repository.RoleRepository;
import com.example.vet.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(UserRequest request) {
        Role role = roleRepository.findByAuthority(request.getRole());
        if (role == null) {
            throw new IllegalStateException("Role not found: " + request.getRole());
        }

        User user = UserMapper.toEntity(request, Collections.singleton(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userRepository.save(user);
        return UserMapper.toDto(saved);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }
}
