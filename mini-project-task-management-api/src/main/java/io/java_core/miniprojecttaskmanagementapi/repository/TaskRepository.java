package io.java_core.miniprojecttaskmanagementapi.repository;

import io.java_core.miniprojecttaskmanagementapi.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();
    Optional<Task> findById(String id);
    Task save(Task task);
    void deleteById(String id);
}
