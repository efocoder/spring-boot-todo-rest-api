package com.efocoder.todoapp.auth;

import com.efocoder.todoapp.auth.dto.RegistrationDto;
import com.efocoder.todoapp.shared.ApiCodes;
import com.efocoder.todoapp.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse> register(
            @RequestBody @Valid RegistrationDto registrationDto
            ){
        var user = authService.register(registrationDto);
        var apiResponse =  ApiResponse.builder()
                .code(ApiCodes.SUCCESS.getCode())
                .message("User registered successfully, Please login.")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
