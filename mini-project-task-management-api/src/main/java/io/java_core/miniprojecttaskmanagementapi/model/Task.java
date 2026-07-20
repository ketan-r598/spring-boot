package io.java_core.miniprojecttaskmanagementapi.model;

import java.io.Serializable;

public record Task(String id, String title, String description, TaskStatus status) implements Serializable { }