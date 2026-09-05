package com.todolist.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.todolist.database.ConnectionFactory;
import com.todolist.dto.TaskDTO;

public class TaskRepository {

    public void salvarTask(TaskDTO task){

    
        System.out.println("#CONECTADO AO DB");
        String sql = "INSERT INTO tasks (nome, descricao, feito) VALUES (?, ?, ?)";

         try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

                System.out.println("Conexão Feita");

            statement.setString(1, task.nome());
            statement.setString(2, task.descricao());
            statement.setBoolean(3, task.feito());

            statement.executeUpdate();

            System.out.println("Tarefa adicionada em DataBase");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void MarcarTask(TaskDTO task){

        String sql = "UPDATE tasks SET feito = 1 WHERE nome = ?";

         try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

                System.out.println("Conexão Feita");

            statement.setString(1, task.nome());
            

            statement.executeUpdate();

            System.out.println("Tarefa adicionada em DataBase");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void desmarcarTask(TaskDTO task){

        String sql = "UPDATE tasks SET feito = 0 WHERE nome = ?";

         try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

                System.out.println("Conexão Feita");

            statement.setString(1, task.nome());
          

            statement.executeUpdate();

            System.out.println("Tarefa adicionada em DataBase");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<TaskDTO> listarTarefas() {

    List<TaskDTO> tarefas = new ArrayList<>();

    String sql = "SELECT t.nome, t.descricao,t.feito  FROM tasks t";

    try (Connection connection = ConnectionFactory.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery()) {

        while (result.next()) {

            TaskDTO task = new TaskDTO(
                result.getString("nome"),
                result.getString("descricao"),
                result.getBoolean("feito")
            );

            tarefas.add(task);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return tarefas;
}

    public void excluirTask(TaskDTO task){

        String sql = "DELETE FROM tasks WHERE nome = ?;";

         try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

                System.out.println("Conexão Feita");

            statement.setString(1, task.nome());
            

            statement.executeUpdate();

            System.out.println("Tarefa adicionada em DataBase");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
