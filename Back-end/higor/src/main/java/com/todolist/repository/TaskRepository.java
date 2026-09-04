package com.todolist.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public void ListarTask(TaskDTO task){

        String sql = "select t.nome, t.descricao from tasks t";

         try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.executeUpdate();

            System.out.println("Tarefa adicionada em DataBase");

        } catch (SQLException e) {
            e.printStackTrace();
        }

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
