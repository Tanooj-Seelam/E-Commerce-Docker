package com.example.ecommerce.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String name;
    private List<String> roles;

    public JwtResponse(String jwt, String token, Long id, String username, String email, String name, List<String> roles) {
        this.accessToken = jwt;
        this.refreshToken = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.roles = roles;
    }
}