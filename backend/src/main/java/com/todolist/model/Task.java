package com.todolist.model;

public class Task {

    private int id;
    private String nome;
    private String descricao;
    private boolean feito;
    

    public Task() {
    }

    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public boolean getFeito() {
        return feito;
    }
    public void setFeito(boolean feito) {
        this.feito = feito;
    } 

    


}
