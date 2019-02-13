package ru.inno.project.dao;

import ru.inno.project.entity.User;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDAOImplTest {
    private static final Logger LOGGER = Logger.getLogger(UserDAOImplTest.class);
    private static UserDAO userDAO;

    private static void init() {
        LOGGER.info("init");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*
         *  параметры для подключения к БД
         */
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String login = "postgres";
        String pass = "";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, login, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        userDAO = new UserDAOImpl(connection);
    }

    @org.junit.Test
    public void createTest() {
        init();

        LOGGER.info("create user test");
        User user = new User();

        user.setLogin("test");
        user.setPassword("123");
        user.setName("TTT");
        user.setEmail("ttt@ya.ru");
        user.setPhone_number("+70000000000");
        user.setRole_id(1);
        user.setCity_id(1);
        user.setActual(true);

        LOGGER.info(user.toString());
        userDAO.create(user);
    }

    @org.junit.Test
    public void selectByIdTest() {
        LOGGER.info("select user by id test");
        init();
        int id = 4;
        User user = userDAO.selectById(id);
        LOGGER.info("Selected user by id = " + id + " " + user.toString());
        id = 1;
        user = userDAO.selectById(id);
        LOGGER.info("Selected user by id = " + id + " " + user.toString());
    }
}
