package com.todolist.service;

import java.util.List;

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

    public void excluirTarefa(TaskDTO task) {
        taskRepository.excluirTask(task);
    }

    public List<TaskDTO> listarTarefas() {
        return taskRepository.listarTarefas();
    }
}