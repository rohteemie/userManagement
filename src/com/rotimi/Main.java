package com.rotimi;

import java.util.List;

public class Main {
    /**
     * Main entry point: starts HTTP server and sets up endpoints
     */
    public static void main(String[] args) {
        try {
            // Create HTTP server on port 8000
            com.sun.net.httpserver.HttpServer server = com.sun.net.httpserver.HttpServer.create(new java.net.InetSocketAddress(8000), 0);
            UserRepository repo = new UserRepository();
            com.google.gson.Gson gson = new com.google.gson.Gson();

            // GET /users - list all users
            server.createContext("/users", exchange -> {
                if ("GET".equals(exchange.getRequestMethod())) {
                    List<User> users = repo.findAll();
                    String response = gson.toJson(users);
                    exchange.getResponseHeaders().add("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.close();
                } else if ("POST".equals(exchange.getRequestMethod())) {
                    // POST /users - create user
                    String body = new String(exchange.getRequestBody().readAllBytes());
                    User user = gson.fromJson(body, User.class);
                    repo.save(user);
                    String response = "{\"message\":\"User created\"}";
                    exchange.getResponseHeaders().add("Content-Type", "application/json");
                    exchange.sendResponseHeaders(201, response.getBytes().length);
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.close();
                } else {
                    exchange.sendResponseHeaders(405, -1); // Method Not Allowed
                    exchange.close();
                }
            });

            // /users/{id} endpoints
            server.createContext("/users/", exchange -> {
                String path = exchange.getRequestURI().getPath();
                String[] parts = path.split("/");
                if (parts.length < 3) {
                    exchange.sendResponseHeaders(400, -1); // Bad Request
                    exchange.close();
                    return;
                }
                int id;
                try {
                    id = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                    exchange.sendResponseHeaders(400, -1);
                    exchange.close();
                    return;
                }

                if ("GET".equals(exchange.getRequestMethod())) {
                    User user = repo.findById(id);
                    if (user == null) {
                        exchange.sendResponseHeaders(404, -1);
                    } else {
                        String response = gson.toJson(user);
                        exchange.getResponseHeaders().add("Content-Type", "application/json");
                        exchange.sendResponseHeaders(200, response.getBytes().length);
                        exchange.getResponseBody().write(response.getBytes());
                    }
                    exchange.close();
                } else if ("PUT".equals(exchange.getRequestMethod())) {
                    String body = new String(exchange.getRequestBody().readAllBytes());
                    User update = gson.fromJson(body, User.class);
                    boolean updated = repo.update(id, update.getName(), update.getEmail());
                    String response = updated ? "{\"message\":\"User updated\"}" : "{\"error\":\"User not found\"}";
                    exchange.getResponseHeaders().add("Content-Type", "application/json");
                    exchange.sendResponseHeaders(updated ? 200 : 404, response.getBytes().length);
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.close();
                } else if ("DELETE".equals(exchange.getRequestMethod())) {
                    boolean deleted = repo.delete(id);
                    String response = deleted ? "{\"message\":\"User deleted\"}" : "{\"error\":\"User not found\"}";
                    exchange.getResponseHeaders().add("Content-Type", "application/json");
                    exchange.sendResponseHeaders(deleted ? 200 : 404, response.getBytes().length);
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.close();
                } else {
                    exchange.sendResponseHeaders(405, -1);
                    exchange.close();
                }
            });

            System.out.println("Server started on http://localhost:8000");
            server.start();
        } catch (Exception e) {
            System.err.println("Failed to start server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
