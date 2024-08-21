package com.unimater.dao;

import com.unimater.model.SaleItem;

import java.sql.Connection;

public class SaleItemDAO extends GenericDAOImpl<SaleItem> implements GenericDAO<SaleItem>{
    private Connection connection;
    private final String TABLE_NAME = "sale_item";

    public SaleItemDAO(Connection connection) {
        super(SaleItem::new, connection);
        super.tableName = TABLE_NAME;
    }
}
