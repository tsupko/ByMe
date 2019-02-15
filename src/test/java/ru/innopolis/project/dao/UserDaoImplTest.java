package ru.innopolis.project.dao;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.project.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImplTest.class);
    private static UserDao userDAO;
    private static Connection connection;

    private static void init() {
        LOGGER.info("init");

        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/byme",
                    "postgres",
                    "2019src14"
            );
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("Исключение при установке соединения с базой данных: {}", e);
        }

        userDAO = new UserDaoImpl(connection);
    }

    @Test
    void createTest() {
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
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void selectByIdTest() {
        LOGGER.info("select user by id test");
        init();
        int id = 4;
        User user = userDAO.selectById(id);
        LOGGER.info("Selected user by id = " + id + " " + user.toString());
        id = 1;
        user = userDAO.selectById(id);
        LOGGER.info("Selected user by id = " + id + " " + user.toString());
    }

    @Test
    void existsTest() {
        LOGGER.info("existsTest");
        init();
        String login = "admin";
        String password = "admin";
        userDAO.exists(login, password);
        assertTrue(userDAO.exists(login, password));
    }

    @Test
    void existsPositiveTest() {
        LOGGER.info("existsTest");
        init();
        String login = "gfdsuygj";
        String password = "sfd";
        userDAO.exists(login, password);
        assertFalse(userDAO.exists(login, password));
    }

    @Test
    void existsNegativeTest() {
        LOGGER.info("existsTest");
        init();
        String login = " ";
        String password = " ";
        userDAO.exists(login, password);
    }
}
