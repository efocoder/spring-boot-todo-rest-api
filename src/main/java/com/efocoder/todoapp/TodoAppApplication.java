package com.efocoder.todoapp;

import com.efocoder.todoapp.role.Role;
import com.efocoder.todoapp.role.RoleRepository;
import com.efocoder.todoapp.shared.StatusEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TodoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(RoleRepository roleRepository){
        return args -> {
            if(roleRepository.findByName("USER").isEmpty()){
                roleRepository.save(
                        Role.builder()
                                .name("USER")
                                .status(StatusEnum.ACTIVE)
                                .build()
                );
            }
        };
    }

}
