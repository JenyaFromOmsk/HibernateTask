package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Connection connect = Util.connection();
        try (Statement statement = connect.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(40) NOT NULL, " +
                    " lastName VARCHAR (40) NOT NULL, " +
                    " age INT not NULL, " +
                    " PRIMARY KEY (id))");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Connection connect = Util.connection();
        try (Statement statement = connect.createStatement()) {
            statement.execute("DROP TABLE if exists users");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connect = Util.connection();
        try (PreparedStatement statement = connect.prepareStatement(
                "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
            System.out.println(name + " - добавлен в базу!");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        Connection connect = Util.connection();
        try (PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM users WHERE id=?")) {
            preparedStatement.setLong(1, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connect = Util.connection();
        try (Statement statement = connect.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM users");
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong(1));
                user.setName(result.getString(2));
                user.setLastName(result.getString(3));
                user.setAge(result.getByte(4));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        Connection connect = Util.connection();
        try (Statement statement = connect.createStatement()) {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
