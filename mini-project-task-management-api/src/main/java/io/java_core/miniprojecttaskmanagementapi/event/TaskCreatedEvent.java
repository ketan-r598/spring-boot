package io.java_core.miniprojecttaskmanagementapi.event;

import io.java_core.miniprojecttaskmanagementapi.model.Task;
import org.springframework.context.ApplicationEvent;

public class TaskCreatedEvent extends ApplicationEvent {
    private Task task;

    public TaskCreatedEvent(Object source, Task task) {
        super(source);
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
