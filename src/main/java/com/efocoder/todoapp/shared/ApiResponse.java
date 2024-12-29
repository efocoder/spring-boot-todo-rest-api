package com.efocoder.todoapp.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiResponse {
    private Integer code;
    private String message;
    private Collection<?> data;
}