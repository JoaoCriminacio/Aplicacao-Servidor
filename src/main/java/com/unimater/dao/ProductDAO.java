package com.unimater.dao;

import com.unimater.model.Product;

import java.sql.Connection;
import java.util.List;

public class ProductDAO extends GenericDAOImpl<Product> implements GenericDAO<Product> {

    private Connection connection;
    private final String TABLE_NAME = "product";

    private final List<String> COLUMNS = List.of("description");

    private final ProductTypeDAO productTypeDAO;

    public ProductDAO(Connection connection) {
        super(Product::new, connection);
        super.tableName = TABLE_NAME;
        super.columns = COLUMNS;
        this.productTypeDAO = new ProductTypeDAO(connection);
    }

    public Product feedProductType(Connection connection, Product product){
        product.setProductType(productTypeDAO.getById(product.getProductType().getId()));
        return product;
    }

}
