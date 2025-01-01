package com.efocoder.todoapp.task.dto;

import com.efocoder.todoapp.shared.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
public class TaskDto {
    private UUID id;
    private String title;
    private String description;
    private StatusEnum status;
    @JsonProperty("created_date")
    private LocalDateTime createdDate;
    @JsonProperty("last_modified")
    private LocalDateTime lastModifiedDate;
    @JsonProperty("owner_id")
    private UUID userId;
}
