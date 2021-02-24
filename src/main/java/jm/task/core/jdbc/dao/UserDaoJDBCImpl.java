package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final String tName = "users";
    private Util util = new Util();

    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        try (Connection con = util.getConnection(); Statement state = con.createStatement()) {
            state.execute("CREATE TABLE IF NOT EXISTS " + tName +
                    "(id BIGINT(19) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(25) NOT NULL, lastname VARCHAR(45) NOT NULL, " +
                    "age TINYINT UNSIGNED NOT NULL) DEFAULT CHARSET=utf8");
        } catch (SQLException e) {
            System.out.println("Unable to create " + tName + " table: " + e);
        }
    }

    public void dropUsersTable() {
        try (Connection con = util.getConnection(); Statement state = con.createStatement()){
            state.execute("DROP TABLE IF EXISTS " + tName);
        } catch (SQLException e) {
            System.out.println("Unable to drop " + tName + " table: " + e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection con = util.getConnection(); Statement state = con.createStatement()) {
            state.executeUpdate("INSERT INTO " + tName + "(name, lastName, age) " +
                "VALUES ('" + name + "', '" + lastName + "', '" + age + "')");
            System.out.println("User " + name + " is added to DB");
        } catch (SQLException e) {
            System.out.println("Unable to add " + name + " to " + tName + " table: " + e);
        }
    }

    public void removeUserById(long id) {
        try (Connection con = util.getConnection(); Statement state = con.createStatement()) {
            state.execute("DELETE FROM " + tName + " WHERE id = " + id);
        } catch (SQLException e) {
            System.out.println("Unable to delete user with id " + id + ": " + e);
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = util.getConnection(); Statement state = con.createStatement()){
            ResultSet resSet = state.executeQuery("SELECT * FROM " + tName);
            while (resSet.next()) {
                User user = new User(resSet.getString(2), resSet.getString(3),
                        resSet.getByte(4));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Unable to get users from DB " + tName + ": " + e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection con = util.getConnection(); Statement state = con.createStatement()){
            state.execute("TRUNCATE TABLE " + tName);
        } catch (SQLException e) {
            System.out.println("Unable to drop " + tName + " table: " + e);
        }
    }
}
