package io.java_core.miniprojecttaskmanagementapi;

import io.java_core.miniprojecttaskmanagementapi.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MiniProjectTaskManagementApiApplicationTests {

    @Autowired
    private TaskService taskService;

    @Test
    void contextLoads() {
        assertThat(taskService).isNotNull();
    }
}