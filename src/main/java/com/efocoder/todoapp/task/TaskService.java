package com.efocoder.todoapp.task;

import com.efocoder.todoapp.exception.RecordNotFoundException;
import com.efocoder.todoapp.shared.StatusEnum;
import com.efocoder.todoapp.task.dto.CreateTaskDto;
import com.efocoder.todoapp.task.dto.TaskDto;
import com.efocoder.todoapp.task.dto.UpdateStatusDto;
import com.efocoder.todoapp.task.dto.UpdateTaskDto;
import com.efocoder.todoapp.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<TaskDto> getAllTasks() {
        var tasks =  taskRepository.findAll(StatusEnum.listStatuses, getUser().getId());
        return tasks.stream().map(this::mapToTaskDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TaskDto findById(UUID id) {
        var task = taskRepository.findById(id, StatusEnum.listStatuses, getUser().getId());
        if (task == null) throw new RecordNotFoundException("Task with ID " + id + " not found.");
        return mapToTaskDto(task);
    }

    @Transactional
    public Task createTask(CreateTaskDto createTaskDto) {
        Task task = null;
        try {
                User user = getUser();
                task = Task.builder()
                        .title(createTaskDto.getTitle())
                        .description(createTaskDto.getDescription())
                        .status(StatusEnum.ACTIVE)
                        .user(user)
                        .build();

               taskRepository.save(task);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong");
        }
        return task;
    }
    @Transactional
    public TaskDto updateTask(UUID id, UpdateTaskDto updateTaskDto) {
        var task = taskRepository.findById(id, StatusEnum.listStatuses, getUser().getId());
        if (task == null) throw new RecordNotFoundException("Task with ID " + id + " not found.");

        if(updateTaskDto.getTitle() != null) task.setTitle(updateTaskDto.getTitle());
        if(updateTaskDto.getDescription() != null) task.setTitle(updateTaskDto.getDescription());
        try {
            taskRepository.save(task);
            return mapToTaskDto(task);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong while updating the task");
        }
    }


    @Transactional
    public void deleteTask(UUID id) {
        var task = taskRepository.findById(id, StatusEnum.listStatuses, getUser().getId());
        if (task == null) throw new RecordNotFoundException("Task with ID " + id + " not found.");
        try {
            task.setStatus(StatusEnum.DELETED);
            taskRepository.save(task);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong while deleting the task");
        }

    }

    @Transactional
    public TaskDto updateTaskStatus(UUID id, UpdateStatusDto statusDto){
        var status = statusDto.getStatus();
        System.out.println("Updating status: " + status);
        if (status == StatusEnum.DELETED) throw new RuntimeException("You cannot set this status");

        var task = taskRepository.findById(id, StatusEnum.listStatuses, getUser().getId());
        if (task == null) throw new RecordNotFoundException("Task with ID " + id + " not found.");

        try {
            task.setStatus(status);
            taskRepository.save(task);
            return mapToTaskDto(task);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong while deleting the task");
        }
    }


    private TaskDto mapToTaskDto(Task task){
        return TaskDto.builder()
               .id(task.getId())
               .title(task.getTitle())
               .description(task.getDescription())
               .status(task.getStatus())
                .createdDate(task.getCreatedDate())
                .lastModifiedDate(task.getLastModifiedDate())
                .userId(task.getUser().getId())
               .build();
    }
    private User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            throw new RuntimeException("User not authenticated");
        }

        return (User) authentication.getPrincipal();
    }
}
