package io.java_core.miniprojecttaskmanagementapi.service;

import io.java_core.miniprojecttaskmanagementapi.configuration.TaskProperties;
import io.java_core.miniprojecttaskmanagementapi.event.TaskCreatedEvent;
import io.java_core.miniprojecttaskmanagementapi.model.AuditEntry;
import io.java_core.miniprojecttaskmanagementapi.model.Task;
import io.java_core.miniprojecttaskmanagementapi.model.TaskStatus;
import io.java_core.miniprojecttaskmanagementapi.repository.TaskRepository;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class TaskService {

    private final TaskRepository taskRepo;
    private final ApplicationEventPublisher eventPublisher;
    private final TaskProperties taskProperties;

    private final ObjectFactory<AuditEntry> auditEntry;

    public TaskService(TaskRepository taskRepo, ApplicationEventPublisher eventPublisher, ObjectFactory<AuditEntry> auditEntry, TaskProperties taskProperties) {
        this.taskRepo = taskRepo;
        this.eventPublisher = eventPublisher;
        this.auditEntry = auditEntry;
        this.taskProperties = taskProperties;
    }

    public Task createTask(String title, String description) {

        System.out.println("Max task limit: " + taskProperties.getLimits().getMaxTasks());
        if (taskRepo.findAll().size() >= taskProperties.getLimits().getMaxTasks()) {
            throw new IllegalArgumentException("Task Limit Exceeded...");
        }

        Task savedTask = taskRepo.save(new Task(UUID.randomUUID().toString(), title, description, TaskStatus.PENDING));


        eventPublisher.publishEvent(new TaskCreatedEvent(this, savedTask));

        System.out.println();
        System.out.println(" [AUDIT] | Task Created | " + auditEntry.getObject() + " | [ " + savedTask.id() + " ]");
        System.out.println();

        return savedTask;


    }

    public Task completeTask(String id) {
        Task oldTask = taskRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id..."));

        taskRepo.deleteById(id);
        Task newTask = new Task(oldTask.id(), oldTask.title(), oldTask.description(), TaskStatus.COMPLETED);
        newTask = taskRepo.save(newTask);

        eventPublisher.publishEvent(new io.java_core.miniprojecttaskmanagementapi.event.TaskCompletedEvent(this, newTask));

        System.out.println();
        System.out.println(" [AUDIT] | Task Completed | " + auditEntry.getObject() + " | [ " + newTask.id() + " ]");
        System.out.println();

        return newTask;
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }
}