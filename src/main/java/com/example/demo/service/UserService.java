package com.example.demo.service;

import com.example.demo.user.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class UserService {
    public void create(User user) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "INSERT INTO user(name,surname,age,username,password)VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.bCryptPassword());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public ArrayList<User> findAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "SELECT * FROM user";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setAge(resultSet.getInt("age"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return users;
    }

    public User findUserById(int id) {
        User user = new User();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "SELECT * FROM user WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setAge(resultSet.getInt("age"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return user;
    }

    public void update(int id, User user) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "UPDATE user SET name = ?,surname = ?,age = ?,username = ?,password = ? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.bCryptPassword());
            preparedStatement.setInt(6, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void delete(int id) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "DELETE FROM user WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public boolean login(String username, String password) {
        User user = new User();

        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "SELECT password FROM user WHERE username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return BCrypt.checkpw(password, user.getPassword());
    }
}
