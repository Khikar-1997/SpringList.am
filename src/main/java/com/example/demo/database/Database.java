package com.example.demo.database;

import com.example.demo.service.MariaDbConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Database {
    private static Database instance = new Database();

    private Database() {
    }

    private void createDatabase() {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL_SEREVER, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "CREATE DATABASE list1";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    private void createUserTable() {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "CREATE TABLE user" +
                    "(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(255) NOT NULL ," +
                    "surname VARCHAR(255) NOT NULL ," +
                    "age INT NOT NULL ," +
                    "username VARCHAR(255) NOT NULL ," +
                    "password VARCHAR(255) NOT NULL ," +
                    "PRIMARY KEY (id)" +
                    ");";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    private void createProductTable() {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "CREATE TABLE product" +
                    "(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(255) NOT NULL ," +
                    "prise DOUBLE NOT NULL ," +
                    "type ENUM('ARTS_AND_CRAFTS'," +
                    "    'AUTOMOTIVE'," +
                    "    'BABY'," +
                    "    'BOOKS'," +
                    "    'COMPUTERS'," +
                    "    'CARS'," +
                    "    'ELECTRONICS'," +
                    "    'REALTY'), " +
                    "uuid VARCHAR(255) NOT NULL ," +
                    "PRIMARY KEY (id)" +
                    ");";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public static void databaseInitializer() {
        Database database = Database.instance;
        database.createDatabase();
        database.createUserTable();
        database.createProductTable();
    }
}
