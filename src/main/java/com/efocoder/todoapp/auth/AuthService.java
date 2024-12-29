package com.efocoder.todoapp.auth;

import com.efocoder.todoapp.auth.dto.RegistrationDto;
import com.efocoder.todoapp.exception.UniqueConstraintViolationException;
import com.efocoder.todoapp.role.RoleRepository;
import com.efocoder.todoapp.shared.StatusEnum;
import com.efocoder.todoapp.user.User;
import com.efocoder.todoapp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Transactional
    public User register(RegistrationDto registrationDto){
      var userRole = roleRepository.findByName("USER")
              .orElseThrow(() -> new IllegalStateException("Role not found"));

      var cUseEmail = userRepository.findByEmail(registrationDto.getEmail());
      var cUseUsername = userRepository.findByUsername(registrationDto.getUsername());

      if(cUseEmail.isPresent()) throw new UniqueConstraintViolationException("Email already exists");
      if(cUseUsername.isPresent()) throw new UniqueConstraintViolationException("username already exists");

      User user;
      try{
        user = User.builder()
                .firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .email(registrationDto.getEmail())
                .username(registrationDto.getUsername())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .status(StatusEnum.ACTIVE)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
      }catch (Exception e) {
        throw new RuntimeException(e);
    }

      return user;
  }
}
