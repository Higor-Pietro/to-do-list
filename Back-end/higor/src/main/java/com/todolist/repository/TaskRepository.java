package com.todolist.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.todolist.database.ConnectionFactory;
import com.todolist.dto.TaskDTO;

public class TaskRepository {

    public void salvarTask(TaskDTO task){

        String sql = "INSERT INTO tasks (nome, descricao, feito) VALUES (?, ?, ?)";

         try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, task.nome());
            statement.setString(2, task.descricao());
            statement.setBoolean(3, task.feito());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void MarcarTask(){

    }

    public void desmarcarTask(){

    }

    public void ListarTask(){

    }

    public void excluirTask(){

    }

}
