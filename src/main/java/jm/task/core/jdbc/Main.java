package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final UserService userService = new UserServiceImpl();
        List<User> users = new ArrayList<>();
        users.add(new User("Ivan", "Ivanov", (byte) 25));
        users.add(new User("Petr", "Petrov", (byte) 26));
        users.add(new User("Maks", "Ivanov", (byte) 27));
        users.add(new User("Sava", "Ivanov", (byte) 28));

        userService.createUsersTable();
        for (User user : users) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
        }
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
