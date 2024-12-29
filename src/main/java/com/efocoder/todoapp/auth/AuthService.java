package com.efocoder.todoapp.auth;

import com.efocoder.todoapp.auth.dto.LoginDto;
import com.efocoder.todoapp.auth.dto.RegistrationDto;
import com.efocoder.todoapp.exception.UniqueConstraintViolationException;
import com.efocoder.todoapp.jwt.JwtService;
import com.efocoder.todoapp.role.RoleRepository;
import com.efocoder.todoapp.shared.ApiCodes;
import com.efocoder.todoapp.shared.ApiResponse;
import com.efocoder.todoapp.shared.StatusEnum;
import com.efocoder.todoapp.user.User;
import com.efocoder.todoapp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private ApiResponse apiResponse;

    @Transactional
    public ApiResponse register(RegistrationDto registrationDto){
      var userRole = roleRepository.findByName("USER")
              .orElseThrow(() -> new IllegalStateException("Role not found"));

      var cUseEmail = userRepository.findByEmail(registrationDto.getEmail());
      var cUseUsername = userRepository.findByUsername(registrationDto.getUsername());

      if(cUseEmail.isPresent()) throw new UniqueConstraintViolationException("Email already exists");
      if(cUseUsername.isPresent()) throw new UniqueConstraintViolationException("username already exists");

      try{
             var user = User.builder()
                .firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .email(registrationDto.getEmail())
                .username(registrationDto.getUsername())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .status(StatusEnum.ACTIVE)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);

        apiResponse = ApiResponse.builder()
                .code(ApiCodes.SUCCESS.getCode())
                .message("User registered successfully, Please login.")
                .build();

        }catch (Exception e) {
        throw new RuntimeException(e);
    }

      return apiResponse;
  }

  public ApiResponse login(LoginDto loginDto){
      var auth = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      loginDto.getUsername(),
                      loginDto.getPassword()
              )
      );
      var claims = new HashMap<String, Object>();
      var user = ((User) auth.getPrincipal());
      claims.put("fullName", user.fullName());
      var jwtToken = jwtService.generateToken(claims, user);
      apiResponse = ApiResponse.builder()
              .code(ApiCodes.SUCCESS.getCode())
              .message("Login successful")
              .token(jwtToken)
              .build();

      return  apiResponse;

  }



}
