package com.efocoder.todoapp.task;

import com.efocoder.todoapp.shared.ApiCodes;
import com.efocoder.todoapp.shared.ApiResponse;
import com.efocoder.todoapp.shared.StatusEnum;
import com.efocoder.todoapp.task.dto.CreateTaskDto;
import com.efocoder.todoapp.task.dto.UpdateStatusDto;
import com.efocoder.todoapp.task.dto.UpdateTaskDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

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

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder()
                .code(ApiCodes.SUCCESS.getCode())
                .message(ApiCodes.SUCCESS.getMessage())
                .data(task)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTaskById(@PathVariable @Valid  UUID id) {
        var task = taskService.findById(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .code(ApiCodes.SUCCESS.getCode())
                .message(ApiCodes.SUCCESS.getMessage())
                .data(task)
                .build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTask(@PathVariable @Valid UUID id, @RequestBody @Valid UpdateTaskDto updateTaskDto) {
        var task = taskService.updateTask(id, updateTaskDto);
        return ResponseEntity.ok(ApiResponse.builder()
                .code(ApiCodes.SUCCESS.getCode())
                .message(ApiCodes.SUCCESS.getMessage())
                .data(task)
                .build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse> updateTaskStatus(@PathVariable @Valid UUID id, @RequestBody @Valid UpdateStatusDto status){
        var task = taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok(ApiResponse.builder()
                .code(ApiCodes.SUCCESS.getCode())
                .message(ApiCodes.SUCCESS.getMessage())
                .data(task)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable @Valid UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.builder()
                .code(ApiCodes.SUCCESS.getCode())
                .message("Record deleted successfully")
                .build());
    }
}
