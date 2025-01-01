package com.efocoder.todoapp.task;

import com.efocoder.todoapp.shared.StatusEnum;
import com.efocoder.todoapp.task.dto.CreateTaskDto;
import com.efocoder.todoapp.task.dto.TaskDto;
import com.efocoder.todoapp.user.User;
import com.efocoder.todoapp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;


    public List<TaskDto> getAllTasks() {
        var tasks =  taskRepository.findAll(StatusEnum.ACTIVE, getUser().getId());
        return tasks.stream().map(this::mapToTaskDto).collect(Collectors.toList());
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
