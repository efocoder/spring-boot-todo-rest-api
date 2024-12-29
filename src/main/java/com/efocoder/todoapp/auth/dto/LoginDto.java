package com.efocoder.todoapp.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginDto {
    @NotEmpty(message = "username required")
    @NotBlank(message = "username required")
    private String username;

    @NotEmpty(message = "password required")
    @NotBlank(message = "password required")
    @Size(min=8)
    private  String password;
}
