package com.todolist.service;

import com.todolist.dto.TaskDTO;
import com.todolist.repository.TaskRepository;

public class TaskService {

    private final TaskRepository taskRepository = new TaskRepository();

    public void criarTarefa(TaskDTO task) {
        taskRepository.salvarTask(task);
    }

    public void alternarStatus(TaskDTO task) {
        taskRepository.MarcarTask(task);
    }
}