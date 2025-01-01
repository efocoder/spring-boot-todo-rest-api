package com.efocoder.todoapp.task.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateTaskDto {
    private String title;
    private String description;
}
