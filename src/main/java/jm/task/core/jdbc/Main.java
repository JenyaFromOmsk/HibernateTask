package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl usi = new UserServiceImpl();
        usi.createUsersTable();
        usi.saveUser("name1", "lastname1", (byte) 1);
        usi.saveUser("name2", "lastname2", (byte) 2);
        usi.saveUser("name3", "lastname3", (byte) 3);
        usi.saveUser("name4", "lastname4", (byte) 4);
        usi.getAllUsers();
        usi.removeUserById(3);
        usi.cleanUsersTable();
        usi.dropUsersTable();
    }
}
