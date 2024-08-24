package com.unimater.dao;


import com.unimater.model.Entity;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class GenericDAOImpl<T extends Entity> implements GenericDAO<T> {

    Connection connection;
    String tableName;

    private Supplier<T> supplier;

    public GenericDAOImpl(Supplier<T> supplier, Connection connection) {
        this.supplier = supplier;
        this.connection = connection;
    }

    @Override
    public List<T> getAll() {
        List<T> returningObejct = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            while (rs.next()) {
                T type = (T) supplier.get().constructFromResultSet(rs, connection);
                returningObejct.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returningObejct;
    }

    @Override
    public T getById(int id) {
        T returningObject = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                returningObject = (T) supplier.get().constructFromResultSet(rs, connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returningObject;
    }

    @Override
    public void upsert(T entity) {
        try {
            PreparedStatement pstmt;
            Field idField = entity.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            int id = (int) idField.get(entity);

            if (entity.getId() == 0) {
                StringBuilder columns = new StringBuilder();
                StringBuilder values = new StringBuilder();
                for (Field field : entity.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (!field.getName().equals("id")) {
                        columns.append(field.getName()).append(",");
                        values.append("?").append(",");
                    }
                }
                columns.setLength(columns.length() - 1); // Remove última vírgula
                values.setLength(values.length() - 1); // Remove última vírgula

                pstmt = connection.prepareStatement("INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")");
                int index = 1;
                for (Field field : entity.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (!field.getName().equals("id")) {
                        pstmt.setObject(index++, field.get(entity));
                    }
                }
            } else {
                StringBuilder setClause = new StringBuilder();
                for (Field field : entity.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (!field.getName().equals("id")) {
                        setClause.append(field.getName()).append(" = ?,");
                    }
                }
                setClause.setLength(setClause.length() - 1); // Remove última vírgula

                pstmt = connection.prepareStatement("UPDATE " + tableName + " SET " + setClause + " WHERE id = ?");
                int index = 1;
                for (Field field : entity.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (!field.getName().equals("id")) {
                        pstmt.setObject(index++, field.get(entity));
                    }
                }
                pstmt.setInt(index, id);
            }
            pstmt.executeUpdate();
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void upsert(T entity) {
//        try {
//            PreparedStatement pstmt;
//            if (entity.getId() == 0) {
//                pstmt = connection.prepareStatement("INSERT INTO " + tableName + " (description) VALUES (?)");
//                pstmt.setString(1, entity.getDescription());
//            } else {
//                pstmt = connection.prepareStatement("UPDATE " + tableName + " SET description = ? WHERE id = ?");
//                pstmt.setString(1, entity.getDescription());
//                pstmt.setInt(2, entity.getId());
//            }
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}