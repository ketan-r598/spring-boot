package io.java_core.miniprojecttaskmanagementapi;

import io.java_core.miniprojecttaskmanagementapi.model.Task;
import io.java_core.miniprojecttaskmanagementapi.service.TaskService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiniProjectTaskManagementApiApplication {

    public static void main(String[] args) {

        try (var context = SpringApplication.run(MiniProjectTaskManagementApiApplication.class, args)) {

            context.registerShutdownHook();

            TaskService taskService = context.getBean(TaskService.class);
            // 1st task

            // Create new Task
            Task task = taskService.createTask("Read Books", "I've to read books A, b and C");

            // Complete the task
            task = taskService.completeTask(task.id());

            // Displaying all the task
            taskService.getAllTasks().forEach(System.out::println);

            // 2nd Task

            // Create new Task
            Task task2 = taskService.createTask("Write Books", "I've to write books A, b and C");

            // Complete the task
            task2 = taskService.completeTask(task2.id());

            // Displaying all the task
            taskService.getAllTasks().forEach(System.out::println);

            System.out.println(Thread.currentThread().getName());
        }
    }
}