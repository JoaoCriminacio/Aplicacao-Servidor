package com.unimater.dao;

import com.unimater.model.ProductType;

import java.sql.*;

public class ProductTypeDAO extends GenericDAOImpl<ProductType> implements GenericDAO<ProductType> {
    private Connection connection;
    private final String TABLE_NAME = "product_type";

    public ProductTypeDAO(Connection connection) {
        super(ProductType::new, connection);
        super.tableName = TABLE_NAME;
    }
}
