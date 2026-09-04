package com.todolist.service;

import com.todolist.dto.TaskDTO;
import com.todolist.repository.TaskRepository;

public class TaskService {

     private final TaskRepository taskRepository = new TaskRepository();

    public void criarTarefa(TaskDTO task) {
        
      
      System.out.println("Entrou Service");

       taskRepository.salvarTask(task);
       
       System.out.println("Tarefa salva!: " + task.nome() + 
       " /Descrição: " + task.descricao() + 
       " /Feito: " + task.feito()); 
    }
}
