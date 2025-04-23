package com.example.ecommerce.security.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;
} 