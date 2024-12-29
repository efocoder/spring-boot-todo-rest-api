package com.efocoder.todoapp.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class RegistrationDto {
    @NotEmpty(message = "first name required")
    @NotBlank(message = "first name required")
    private String username;

    @NotEmpty(message = "first name required")
    @NotBlank(message = "first name required")
    @JsonProperty("first_name")
    private String firstName;

    @NotEmpty(message = "last name required")
    @NotBlank(message = "last name required")
    @JsonProperty("last_name")
    private String lastName;

    @NotEmpty(message = "Email required")
    @NotBlank(message = "Email required")
    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty(message = "password required")
    @NotBlank(message = "password required")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password not strong enough"
    )
    private  String password;
}
