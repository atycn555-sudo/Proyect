package com.example.vet.mapper;

import com.example.vet.dto.UserRequest;
import com.example.vet.dto.UserResponse;
import com.example.vet.model.Role;
import com.example.vet.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toEntity(UserRequest dto, Set<Role> roles) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRoles(roles);
        return user;
    }

    public static UserResponse toDto(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setRoles(
                user.getRoles().stream()
                        .map(Role::getAuthority)
                        .collect(Collectors.toSet()));
        return dto;
    }
}
