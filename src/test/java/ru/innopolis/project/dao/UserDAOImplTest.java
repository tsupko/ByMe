package ru.innopolis.project.dao;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.project.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDAOImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImplTest.class);
    private static UserDao userDAO;

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

//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(url, login, pass);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        userDAO = new UserDaoImpl();
    }

    @Test
    public void createTest() {
        init();

        LOGGER.info("create user test");
        User user = new User();

        user.setLogin("test");
        user.setPassword("123");
        user.setName("TTT");
        user.setEmail("ttt@ya.ru");
        user.setPhoneNumber("+70000000000");
        user.setRoleId(1);
        user.setCityId(1);
        user.setActual(true);

        LOGGER.info(user.toString());
        userDAO.create(user);
    }

    @Test
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
