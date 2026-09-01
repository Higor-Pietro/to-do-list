package com.todolist.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.todolist.dto.TaskDTO;

public class TaskController implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) throws IOException {
       
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();


        if (method.equals("GET")){
 
            if (path.equals("/tasks")){
                path = "/tasks.html";   
            }

            Path arquivo = Path.of("Front-end" + path);
            byte[] conteudo = Files.readAllBytes(arquivo);

            exchange.getResponseHeaders().set(
                "Content-Type" ,
                "text/html; charset=UTF-8"
            );

            exchange.sendResponseHeaders(200, conteudo.length);
            exchange.getResponseBody().write(conteudo);
            exchange.getResponseBody().close();
        } 

         if (method.equals("POST")) {

            if (path.equals("/tasks")) {

                String body = new String(
                    exchange.getRequestBody().readAllBytes(),
                    StandardCharsets.UTF_8
                );

                Map<String, String> data = parseFormData(body);

                String nome = data.get("name");
                String descricao = data.get("description");
              

                TaskDTO task = new TaskDTO(
                    nome,
                    descricao,
                    false
                );

                System.out.println("Nome: " + task.nome());
                System.out.println("Descrição: " + task.descricao());
               

                String resposta = "Tarefa recebida com sucesso!";

                byte[] respostaBytes =
                    resposta.getBytes(StandardCharsets.UTF_8);

                exchange.sendResponseHeaders(
                    200,
                    respostaBytes.length
                );

                exchange.getResponseBody().write(respostaBytes);
                exchange.getResponseBody().close();
            }
        }
    }

    private Map<String, String> parseFormData(String body) {

        Map<String, String> data = new HashMap<>();

        String[] fields = body.split("&");

        for (String field : fields) {

            String[] keyValue = field.split("=", 2);

            String key = URLDecoder.decode(
                keyValue[0],
                StandardCharsets.UTF_8
            );

            String value = URLDecoder.decode(
                keyValue[1],
                StandardCharsets.UTF_8
            );

            data.put(key, value);
        }

        return data;
    }
}
