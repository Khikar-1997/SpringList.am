package com.example.demo.service;

import com.example.demo.product.Product;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class ProductService {
    public void create(Product product) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "INSERT INTO product(name,prise,type,uuid)VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrise());
            preparedStatement.setString(3, product.getType());
            preparedStatement.setString(4, product.getUuid());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public ArrayList<Product> selectAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "SELECT * FROM product";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString("name"));
                product.setPrise(resultSet.getDouble("prise"));
                product.setType(resultSet.getString("type"));
                product.setUuid(resultSet.getString("uuid"));
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return products;
    }

    public Product findProductById(int id) {
        Product product = new Product();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "SELECT * FROM product WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product.setName(resultSet.getString("name"));
                product.setPrise(resultSet.getDouble("prise"));
                product.setType(resultSet.getString("type"));
                product.setUuid(resultSet.getString("uuid"));
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return product;
    }

    public void update(int id, Product product) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "UPDATE product SET name = ?,prise = ?,type = ?,uuid = ? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrise());
            preparedStatement.setString(3, product.getType());
            preparedStatement.setString(4, product.getUuid());
            preparedStatement.setInt(5, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void delete(int id) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "DELETE FROM product WHERE id =?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public ArrayList<Product> serch(String serchText) {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "SELECT name FROM product WHERE name LIKE CONCAT('%',?,'%')";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, serchText);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString("name"));
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return products;
    }
}
