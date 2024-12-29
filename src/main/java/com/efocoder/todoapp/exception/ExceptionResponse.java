package com.efocoder.todoapp.exception;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {
    private Integer code;
    private String message;
    private String error;
//    private Set<String> errors;
    private Map<String, String> errors;
}
