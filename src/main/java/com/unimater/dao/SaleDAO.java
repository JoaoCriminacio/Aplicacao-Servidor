package com.unimater.dao;

import com.unimater.model.Sale;

import java.sql.Connection;

public class SaleDAO extends GenericDAOImpl<Sale> implements GenericDAO<Sale>{
    private Connection connection;
    private final String TABLE_NAME = "sale";

    public SaleDAO(Connection connection) {
        super(Sale::new, connection);
        super.tableName = TABLE_NAME;
    }
}
