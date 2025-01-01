package com.efocoder.todoapp.task;

import com.efocoder.todoapp.shared.ApiCodes;
import com.efocoder.todoapp.shared.ApiResponse;
import com.efocoder.todoapp.task.dto.CreateTaskDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

    @PostMapping
    public ResponseEntity<ApiResponse> createTask(@RequestBody @Valid CreateTaskDto createTaskDto) {
        var task = taskService.createTask(createTaskDto);

        return ResponseEntity.ok(ApiResponse.builder()
                .code(ApiCodes.SUCCESS.getCode())
                .message(ApiCodes.SUCCESS.getMessage())
                .data(task)
                .build());
    }
}
