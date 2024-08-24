package com.unimater;

import com.sun.net.httpserver.HttpServer;
import com.unimater.controller.HelloWorldHandler;
import com.unimater.dao.*;
import com.unimater.model.ProductType;
import com.unimater.dao.ProductDAO;
import com.unimater.model.Product;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App
{
    public static void main(String[] args) {
        try{
            HttpServer servidor = HttpServer.create(
                    //Caminho de socket na porta 8080
                    new InetSocketAddress(8080), 0
            );

            servidor.createContext("/helloWorld", new HelloWorldHandler());


            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/your_db",
                    "root",
                    "root"
            );

            ProductTypeDAO productTypeDAO = new ProductTypeDAO(connection);

            //Update
            ProductType alterar = new ProductType(4, "Alterando");
            productTypeDAO.upsert(alterar);

            //Insert
            ProductType criar = new ProductType(3, "Teste");
            productTypeDAO.upsert(criar);

            //Select
            System.out.println(productTypeDAO.getAll());

            //Delete
            productTypeDAO.delete(4);

            //Executor: Numerar/reconhecer as threads
            servidor.setExecutor(null);
            servidor.start();
            System.out.println("Servidor rodando na porta 8080");
        //IOException é um erro do tipo servidor
        }catch (IOException | SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
