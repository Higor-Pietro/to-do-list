package com.todolist.dto;

public record TaskDTO(
    String nome, 
    String descricao, 
    boolean feito) {
}
