package io.java_core.miniprojecttaskmanagementapi.event;

import io.java_core.miniprojecttaskmanagementapi.model.Task;
import org.springframework.context.ApplicationEvent;

public class TaskCompletedEvent extends ApplicationEvent {

    private Task task;

    public TaskCompletedEvent(Object source, Task task) {
        super(source);
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
