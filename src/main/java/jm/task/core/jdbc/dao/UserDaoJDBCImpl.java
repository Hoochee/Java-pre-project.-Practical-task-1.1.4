package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        final String createTable = "CREATE TABLE IF NOT EXISTS Users("
                + "id BIGINT PRIMARY KEY AUTO_INCREMENT,"
                + "name VARCHAR(15),"
                + "lastName VARCHAR(15),"
                + "age TINYINT)";
        final String autoIncrement = "ALTER TABLE users AUTO_INCREMENT=10001";
        try {
            Util.getStatement().executeUpdate(createTable);
            Util.getStatement().executeUpdate(autoIncrement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        final String dropTable = "DROP TABLE IF EXISTS Users";
        try {
            Util.getStatement().executeUpdate(dropTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement statement = Util.getConnection().prepareStatement("INSERT INTO Users VALUES(NULL,?,?,?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement statement = Util.getConnection().prepareStatement("DELETE FROM Users WHERE id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try {
            ResultSet resultSet = Util.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        final String cleanTable = "DELETE FROM Users";
        try {
            Util.getStatement().executeUpdate(cleanTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
