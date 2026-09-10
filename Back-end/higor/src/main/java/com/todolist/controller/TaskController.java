package com.todolist.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.todolist.dto.TaskDTO;
import com.todolist.service.TaskService;

public class TaskController implements HttpHandler {

    private final TaskService taskService = new TaskService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();

        if (method.equals("GET")) {

            if (path.equals("/tasks")) {

                List<TaskDTO> tarefas = taskService.listarTarefas();

                StringBuilder resposta = new StringBuilder("[");
                
                for (int i = 0; i < tarefas.size(); i++) {

                    TaskDTO task = tarefas.get(i);

                    resposta.append("""
                        {
                            "nome": "%s",
                            "descricao": "%s",
                            "feito": %s
                        }
                        """.formatted(
                            escapeJson(task.nome()),
                            escapeJson(task.descricao()),
                            task.feito()
                        ));

                    if (i < tarefas.size() - 1) {
                        resposta.append(",");
                    }
                }

                resposta.append("]");

                byte[] respostaBytes =
                    resposta.toString().getBytes(StandardCharsets.UTF_8);

                exchange.getResponseHeaders().set(
                    "Content-Type",
                    "application/json; charset=UTF-8"
                );

                exchange.sendResponseHeaders(
                    200,
                    respostaBytes.length
                );

                exchange.getResponseBody().write(respostaBytes);
                exchange.getResponseBody().close();

                return;
            }
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

                taskService.criarTarefa(task);

                String resposta = """
                    {
                        "nome": "%s",
                        "descricao": "%s",
                        "feito": false
                    }
                    """.formatted(
                        escapeJson(nome),
                        escapeJson(descricao)
                    );

                byte[] respostaBytes =
                    resposta.getBytes(StandardCharsets.UTF_8);

                exchange.getResponseHeaders().set(
                    "Content-Type",
                    "application/json; charset=UTF-8"
                );

                exchange.sendResponseHeaders(
                    200,
                    respostaBytes.length
                );

                exchange.getResponseBody().write(respostaBytes);
                exchange.getResponseBody().close();

                return;
            }

            if (path.equals("/tasks/status")) {

                String body = new String(
                    exchange.getRequestBody().readAllBytes(),
                    StandardCharsets.UTF_8
                );

                Map<String, String> data = parseFormData(body);

                String nome = data.get("name");

                TaskDTO task = new TaskDTO(
                    nome,
                    "",
                    false
                );

                taskService.alternarStatus(task);

                exchange.sendResponseHeaders(
                    204,
                    -1
                );

                exchange.getResponseBody().close();

                return;
            }
        }

        if (method.equals("DELETE")) {

            if (path.equals("/tasks")) {

                String body = new String(
                    exchange.getRequestBody().readAllBytes(),
                    StandardCharsets.UTF_8
                );

                Map<String, String> data = parseFormData(body);

                String nome = data.get("name");

                TaskDTO task = new TaskDTO(
                    nome,
                    "",
                    false
                );

                taskService.excluirTarefa(task);

                exchange.sendResponseHeaders(
                    204,
                    -1
                );

                exchange.getResponseBody().close();

                return;
            }
        }

        exchange.sendResponseHeaders(404, -1);
        exchange.getResponseBody().close();
    }

    private Map<String, String> parseFormData(String body) {

        Map<String, String> data = new HashMap<>();

        if (body == null || body.isEmpty()) {
            return data;
        }

        String[] fields = body.split("&");

        for (String field : fields) {

            String[] keyValue = field.split("=", 2);

            if (keyValue.length < 2) {
                continue;
            }

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

    private String escapeJson(String value) {

        if (value == null) {
            return "";
        }

        return value
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r");
    }
}