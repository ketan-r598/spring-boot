package io.java_core.miniprojecttaskmanagementapi;

import io.java_core.miniprojecttaskmanagementapi.actuator.TaskRepositoryHealthInfo;
import io.java_core.miniprojecttaskmanagementapi.model.Task;
import io.java_core.miniprojecttaskmanagementapi.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.Status;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("dev")
public class HealthIndicatorTest {

    @MockitoBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskRepositoryHealthInfo healthInfo;

    @Test
    void health_shouldBeUp() {
        when(taskRepository.findAll()).thenReturn(List.of(mock(Task.class)));

        Health health = healthInfo.health();
        assertThat(health.getStatus()).isEqualTo(Status.UP);
    }
}
