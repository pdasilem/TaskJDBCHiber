package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();


        List<User> listUsers = userService.getAllUsers();
        for (User user : listUsers) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
