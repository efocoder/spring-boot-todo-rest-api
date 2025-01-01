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
    @Query("SELECT t FROM Task t JOIN FETCH t.user WHERE t.status IN :statuses AND t.user.id = :user_id")
    List<Task> findAll(@Param("statuses")  List<StatusEnum> statuses, @Param("user_id") UUID user_id);

    @Query("SELECT t FROM Task t WHERE t.id = :id AND t.status IN :statuses AND t.user.id = :user_id")
    Task findById(@Param("id") UUID id, @Param("statuses") List<StatusEnum> statuses, @Param("user_id") UUID user_id);
}
