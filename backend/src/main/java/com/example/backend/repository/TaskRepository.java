package com.example.backend.repository;

import com.example.backend.entity.user.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
