package com.efocoder.todoapp.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateTaskDto {
    @NotEmpty(message = "title required")
    @NotBlank(message = "title required")
    private String title;

    @NotEmpty(message = "description required")
    @NotBlank(message = "description required")
    private String description;

}
