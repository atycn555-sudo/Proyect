package com.example.vet.dto;

import lombok.Data;
import java.util.Set;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private Set<String> roles;
}
