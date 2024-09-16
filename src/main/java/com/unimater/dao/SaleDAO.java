package com.unimater.dao;

import com.unimater.model.Sale;
import com.unimater.model.SaleItem;

import java.sql.Connection;
import java.util.List;

public class SaleDAO extends GenericDAOImpl<Sale> implements GenericDAO<Sale> {

    private Connection connection;
    private final String TABLE_NAME = "sale";

    private final List<String> COLUMNS = List.of("id", "insert_at");

    private SaleItemDAO saleItemDAO;

    public SaleDAO(Connection connection) {
        super(Sale::new, connection);
        super.tableName = TABLE_NAME;
        super.columns = COLUMNS;
        this.connection = connection;
    }

    public Sale feedSaleItems(Sale sale){
        saleItemDAO = new SaleItemDAO(connection);
        sale.setSaleItems(saleItemDAO.findBySaleId(sale.getId()));
        return sale;
    }

    @Override
    public void upsert(Sale object) {
        super.upsert(object);
        saleItemDAO = new SaleItemDAO(connection);
        for (SaleItem item : object.getSaleItems()){
            saleItemDAO.upsert(item, object.getId());
        };
    }
}
