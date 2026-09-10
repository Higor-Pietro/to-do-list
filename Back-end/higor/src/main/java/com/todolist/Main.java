package com.todolist;

import com.sun.net.httpserver.HttpServer;
import com.todolist.controller.MainController;
import com.todolist.controller.TaskController;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0
        );

        server.createContext("/", new MainController());
        server.createContext("/tasks.html", new MainController());
        server.createContext("/tasks", new TaskController());
        server.start();

        System.out.println("Servidor rodando");
    }
}