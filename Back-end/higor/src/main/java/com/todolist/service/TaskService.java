package com.todolist.service;

import com.todolist.dto.TaskDTO;
import com.todolist.model.Task;
import com.todolist.repository.TaskRepository;

public class TaskService {

     private final TaskRepository taskRepository = new TaskRepository();

    public void criarTarefa(TaskDTO task) {
        
       taskRepository.salvarTask(task);
       System.out.println("Tarefa salva!: " + task.nome() ); 
    }
}
