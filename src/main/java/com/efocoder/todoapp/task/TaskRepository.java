package com.efocoder.todoapp.task;

import com.efocoder.todoapp.shared.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    @Query("SELECT t FROM Task t JOIN FETCH t.user WHERE t.status = :status AND t.user.id = :user_id")
    List<Task> findAll(@Param("status") StatusEnum status, @Param("user_id") UUID id);
}
