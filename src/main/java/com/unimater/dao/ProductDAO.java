package com.unimater.dao;

import com.unimater.model.Product;

import java.sql.*;

public class ProductDAO extends GenericDAOImpl<Product> implements GenericDAO<Product>{
    private Connection connection;
    private final String TABLE_NAME = "product";

    public ProductDAO(Connection connection) {
        super(Product::new, connection);
        super.tableName = TABLE_NAME;
    }
}
