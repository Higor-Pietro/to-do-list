package com.todolist.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;  
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

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

        if (method.equals("POST")){

            if(path.equals("/tasks")){
                path = "tasks.html";
            }

            



        }




    }

}
