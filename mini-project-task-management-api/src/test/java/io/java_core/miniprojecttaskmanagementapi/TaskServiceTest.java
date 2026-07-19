package io.java_core.miniprojecttaskmanagementapi;

import io.java_core.miniprojecttaskmanagementapi.model.Task;
import io.java_core.miniprojecttaskmanagementapi.repository.TaskRepository;
import io.java_core.miniprojecttaskmanagementapi.service.TaskService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private MeterRegistry meterRegistry;

    @Mock
    private Counter counter;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        when(meterRegistry.counter("tasks.created")).thenReturn(counter);
        when(meterRegistry.counter("tasks.completed")).thenReturn(counter);
    }

    @Test
    void createTask_shouldCallSaveOnRepository() throws Exception {

        // Arrange
        when(taskRepository.findAll()).thenReturn(List.of());
        when(taskRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        // Act
        Task task = taskService.createTask("Read Books","Desc");

        // Assert
        assertThat(task.title()).isEqualTo("Read Books");
        verify(taskRepository, times(1)).save(any(Task.class));
    }
}
