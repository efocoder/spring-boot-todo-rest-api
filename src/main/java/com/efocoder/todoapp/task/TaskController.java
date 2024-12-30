package com.efocoder.todoapp.task;

import com.efocoder.todoapp.shared.ApiCodes;
import com.efocoder.todoapp.shared.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<ApiResponse> listAllTasks() {
        var tasks = taskService.getAllTasks();
        return ResponseEntity.ok(ApiResponse.builder()
                        .code(ApiCodes.SUCCESS.getCode())
                        .message(ApiCodes.SUCCESS.getMessage())
                        .data(tasks)
                        .build());
    }
}
