package com.todolist.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MainController implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        
        if (method.equals("POST") && path.equals("/shutdown")) {

            String resposta = "Programa finalizado.";

            byte[] respostaBytes =
                    resposta.getBytes("UTF-8");

            exchange.sendResponseHeaders(
                    200,
                    respostaBytes.length
            );

            exchange.getResponseBody().write(respostaBytes);
            exchange.getResponseBody().close();

            // Aguarda a resposta e encerra o programa
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.exit(0);
            }).start();

            return;
        }

        if (method.equals("GET")) {

            if (path.equals("/")) {
                path = "/index.html";
            }

            Path arquivo = Path.of("Front-end" + path);

            byte[] conteudo = Files.readAllBytes(arquivo);

            exchange.getResponseHeaders().set(
                    "Content-Type",
                    "text/html; charset=UTF-8"
            );

            exchange.sendResponseHeaders(
                    200,
                    conteudo.length
            );

            exchange.getResponseBody().write(conteudo);
            exchange.getResponseBody().close();
        }
    }
}