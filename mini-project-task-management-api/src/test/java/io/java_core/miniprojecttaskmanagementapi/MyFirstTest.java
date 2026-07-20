package io.java_core.miniprojecttaskmanagementapi;


import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class MyFirstTest {

    @BeforeAll
    static void init() {
        System.out.println("Setting up database...");
    }

    @BeforeEach
    void setup() {
        System.out.println("Creating fresh objects...");
    }

    @Test
    void shoulDoSomething() {
        System.out.println("Test One...");
    }

    @Test
    void shouldDoSomethingElse() {
        System.out.println("Do some more test");
    }

    @Test
    void assertionDemo() {

        // Equality
        assertEquals(5, 2+3);

        // Boolean
        assertTrue("hello".startsWith("h"));

        // Null / or NotNull
        assertNotNull(new Object());

        // Exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Title cannot be blank");
        });

        assertEquals("Title cannot be blank", exception.getMessage());

        // Multiple at once (all run, failures collected)
        assertAll("task properties",
                () -> assertEquals(5,2+3),
                () -> assertEquals(10,5+5)
                );
    }

    @Test
    void assertjDemo() {
        assertThat(2+3).isEqualTo(5);
        assertThat("hello").startsWith("h").hasSize(5);
        assertThat(List.of(1,2,3)).contains(1).hasSize(3);

        assertThatThrownBy(() -> {throw new IllegalArgumentException("Title cannot be blank");})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("blank");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Cleaning up...");
    }

    @AfterAll
    static void Destroy() {
        System.out.println("Clopsing Database...");
    }
}
