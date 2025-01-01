package com.efocoder.todoapp.task.dto;

import com.efocoder.todoapp.shared.StatusConverter;
import com.efocoder.todoapp.shared.StatusEnum;
import com.efocoder.todoapp.shared.StatusEnumDeserializer;
import com.efocoder.todoapp.validator.ValidStatusEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Convert;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateStatusDto {
    @ValidStatusEnum(message = "Invalid status")
    @JsonDeserialize(using = StatusEnumDeserializer.class)
    private StatusEnum status;
}
