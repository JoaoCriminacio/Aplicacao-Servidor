package com.unimater.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class Sale implements Entity {

    private int id;
    private List<SaleItem> saleItems;
    private Timestamp insertAt;

    public Sale(int id, List<SaleItem> saleItems, Timestamp insertAt) {
        this.id = id;
        this.saleItems = saleItems;
        this.insertAt = insertAt;
    }

    public Sale(ResultSet rs) throws SQLException{
        this.id = rs.getInt("id");
        this.insertAt = rs.getTimestamp("insert_at");
    }

    public Sale() {

    }

    @Override
    public Entity constructFromResultSet(ResultSet rs) throws SQLException {
        return new Sale(rs);
    }

    @Override
    public PreparedStatement prepareStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, getId());
        preparedStatement.setTimestamp(2, Timestamp.from(Instant.now()));
        return preparedStatement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public Timestamp getInsertAt() {
        return insertAt;
    }

    public void setInsertAt(Timestamp insertAt) {
        this.insertAt = insertAt;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", saleItems=" + saleItems +
                ", insertAt=" + insertAt +
                '}';
    }
}
