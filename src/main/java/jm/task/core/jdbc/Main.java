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

        userService.saveUser("Антон", "Лирник", (byte) 15);
        userService.saveUser("Вячеслав", "Бутусов", (byte) 22);
        userService.saveUser("Дмитрий", "Соколов", (byte) 26);
        userService.saveUser("Дмитрий", "Брекоткин", (byte) 34);

        List<User> listUsers = userService.getAllUsers();
        for (User user : listUsers) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
