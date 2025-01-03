package com.efocoder.todoapp.auth;

import com.efocoder.todoapp.auth.dto.LoginDto;
import com.efocoder.todoapp.auth.dto.RegistrationDto;
import com.efocoder.todoapp.jwt.JwtResponse;
import com.efocoder.todoapp.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registrationDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid LoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }
}
