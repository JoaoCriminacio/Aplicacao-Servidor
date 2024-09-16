package com.unimater.controller;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.unimater.dao.SaleItemDAO;
import com.unimater.model.SaleItem;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;

import java.sql.Connection;

public class SaleItemHandler implements HttpHandler {

    private SaleItemDAO saleItemDAO;
    private Connection connection;

    public SaleItemHandler(Connection connection) {
        this.connection = connection;
    }

    private Gson gson = new Gson();

    public void handle(HttpExchange exchange) throws IOException {
        saleItemDAO = new SaleItemDAO(connection);

        if (isGet(exchange)) {
            handleGet(exchange);
        } else if (isPost(exchange)) {
            handlePost(exchange);
        } else if (isPut(exchange)) {
            handlePut(exchange);
        } else if (isDelete(exchange)) {
            handleDelete(exchange);
        } else {
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
        }
    }

    // Handle GET requests
    private void handleGet(HttpExchange exchange) throws IOException {
        String[] segments = segmentPath(exchange);
        if (segments.length > 2) {  // Adjust the index based on your URI structure
            try {
                int id = Integer.parseInt(segments[2]); // Parse the ID
                SaleItem saleItem = saleItemDAO.getById(id);
                if (saleItem != null) {
                    String jsonResponse = gson.toJson(saleItem);
                    exchange.getResponseHeaders().add("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
                    sendOutputJson(exchange, jsonResponse);
                } else {
                    exchange.sendResponseHeaders(404, -1); // Not Found
                }
            } catch (NumberFormatException e) {
                exchange.sendResponseHeaders(400, -1); // Bad Request
            }
        } else {
            exchange.sendResponseHeaders(404, -1); // Not Found
        }
    }

    // Handle POST requests (create new product type)
    private void handlePost(HttpExchange exchange) throws IOException {
        SaleItem saleItem = parseRequestBody(exchange, SaleItem.class);
        if (saleItem != null && saleItem.getId() == 0) { // Assuming ID is 0 for new product
            saleItemDAO.upsert(saleItem); // Insert the new product type
            exchange.sendResponseHeaders(201, -1); // Created
        } else {
            exchange.sendResponseHeaders(400, -1); // Bad Request
        }
    }

    // Handle PUT requests (update existing product type)
    private void handlePut(HttpExchange exchange) throws IOException {
        SaleItem saleItem = parseRequestBody(exchange, SaleItem.class);
        if (saleItem != null && saleItem.getId() > 0) { // Existing product needs a valid ID
            saleItemDAO.upsert(saleItem); // Update the product type
            exchange.sendResponseHeaders(200, -1); // OK
        } else {
            exchange.sendResponseHeaders(400, -1); // Bad Request
        }
    }

    // Handle DELETE requests
    private void handleDelete(HttpExchange exchange) throws IOException {
        String[] segments = segmentPath(exchange);
        if (segments.length > 2) {
            try {
                int id = Integer.parseInt(segments[2]); // Parse the ID
                saleItemDAO.delete(id); // Delete the product type
                exchange.sendResponseHeaders(204, -1); // No Content
            } catch (NumberFormatException e) {
                exchange.sendResponseHeaders(400, -1); // Bad Request
            }
        } else {
            exchange.sendResponseHeaders(404, -1); // Not Found
        }
    }

    // Utility method to parse request body JSON into an object of the given class
    private <T> T parseRequestBody(HttpExchange exchange, Class<T> clazz) throws IOException {
        InputStreamReader reader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        return gson.fromJson(reader, clazz);
    }

    private static void sendOutputJson(HttpExchange exchange, String jsonResponse) throws IOException {
        OutputStream output = exchange.getResponseBody();
        output.write(jsonResponse.getBytes());
        output.flush();
        output.close();
    }

    private static String[] segmentPath(HttpExchange exchange) {
        String path = exchange.getRequestURI().getPath();
        String[] segments = path.split("/");
        return segments;
    }

    private static boolean isGet(HttpExchange exchange) {
        return exchange.getRequestMethod().equals("GET");
    }

    private static boolean isPost(HttpExchange exchange) {
        return exchange.getRequestMethod().equals("POST");
    }

    private static boolean isPut(HttpExchange exchange) {
        return exchange.getRequestMethod().equals("PUT");
    }

    private static boolean isDelete(HttpExchange exchange) {
        return exchange.getRequestMethod().equals("DELETE");
    }
}
