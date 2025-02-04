package com.hexagonal.tasks.domain.ports.in;

import com.hexagonal.tasks.domain.models.Task;

public interface CreateTaskUseCase {
    Task createdTask(Task task);
}
