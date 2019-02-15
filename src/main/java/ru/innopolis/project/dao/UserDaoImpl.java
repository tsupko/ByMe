package ru.innopolis.project.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.innopolis.project.entity.User;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Реализация интерфейса {@code UserDao} для работы с объектами {@code entity.User}.
 *
 * @author Kuzina Anastasia, Александр Цупко
 */
@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    private Connection connection = null;

    public UserDaoImpl() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/byme",
                    "postgres",
                    ""
            );
        } catch (SQLException e) {
            LOGGER.error("Исключение при установке соединения с базой данных: {}", e);
        }
    }

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public UserDaoImpl(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * соответствующие названия полей в таблице user
     */
    private static final String USER_ID = "id";
    private static final String USER_LOGIN = "login";
    private static final String USER_PASSWORD = "password";
    private static final String USER_NAME = "name";
    private static final String USER_EMAIL = "email";
    private static final String USER_PHONE_NUMBER = "phone_number";
    private static final String USER_ROLE_ID = "role_id";
    private static final String USER_CITY_ID = "city_id";
    private static final String USER_IS_ACTUAL = "is_actual";

    /**
     * sql-скрипт для создания записи в соответствующей таблице
     */
    private static final String INSERT_USER = "insert into public.user" +
            " (login, password, name, email, phone_number, role_id, city_id, is_actual)" +
            " values (?, ?, ?, ?, ?, ?, ?, ?) returning id";

    /**
     * создание записи в БД с полями переданного экземпляра
     * в соответствующей таблице
     *
     * @param user объект, для которого будет создана запись в БД
     */
    @Override
    public void create(User user) {
        if (user == null) {
            LOGGER.error("Попытка создавать запись в БД для user == null ");
            return;
        }
        LOGGER.debug("Создание пользователя {}", user);
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_USER)) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhoneNumber());
            stmt.setInt(6, user.getRoleId());
            stmt.setInt(7, user.getCityId());
            stmt.setBoolean(8, user.isActual());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    user.setId(rs.getInt("id"));
                    LOGGER.info("Пользователь с id={} создан успешно", user.getId());
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при возвращении id после создания пользователя: {}", e);
            }
        } catch (SQLException e) {
            LOGGER.error("Исключение при подготовке insert-запроса: {}", e);
        }
    }

    /**
     * sql-скрипт для выборки по id
     */
    private static final String SELECT_USER_BY_ID = "select * from public.user where id = ?";

    /**
     * создание объекта user по переданному id
     *
     * @param id первичный ключ записи в БД
     */
    @Override
    public User selectById(int id) {
        if (id <= 0) {
            LOGGER.error("Некорректный id={}: {}", id);
            return null;
        }
        LOGGER.debug("Выбор пользователя по id={}", id);
        User user = new User();
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_USER_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    user.setId(rs.getInt(USER_ID));
                    user.setLogin(rs.getString(USER_LOGIN));
                    user.setPassword(rs.getString(USER_PASSWORD));
                    user.setName(rs.getString(USER_NAME));
                    user.setEmail(rs.getString(USER_EMAIL));
                    user.setPhoneNumber(rs.getString(USER_PHONE_NUMBER));
                    user.setRoleId(rs.getInt(USER_ROLE_ID));
                    user.setCityId(rs.getInt(USER_CITY_ID));
                    user.setActual(rs.getBoolean(USER_IS_ACTUAL));
                }
                if (user.getId() != 0) {
                    LOGGER.info("{} выбран по id={} успешно", user, id);
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при получении пользователя по id={}: {}", id, e);
            }
        } catch (SQLException e) {
            LOGGER.error("Исключение при подготовке запроса на выбор пользователя по id={}: {}", id, e);
        }
        return user.getId() == 0 ? null : user;
    }

    /**
     * sql-скрипт для проверки наличия пользователя в БД
     */
    private static final String SELECT_BY_LOGIN_PASS = "SELECT * FROM public.user WHERE login = ? AND password = ?";

    /**
     * проверка наличия пользователя с указанными параметрами в таблице user
     *
     * @param login    логин пользователся
     * @param password пароль пользователся
     */
    @Override
    public boolean exists(String login, String password) {
        if (login.trim().isEmpty() && password.trim().isEmpty()) {
            LOGGER.error("Параметры login и  password не могут быть пустыми");
            return false;
        }
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_LOGIN_PASS)) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при проверке наличия пользователя по login={}: {}, password={}: {}",
                        login, password, e);
            }
        } catch (SQLException e) {
            LOGGER.error("Исключение при подготовке запроса проверки наличия пользователя " +
                    "по login={}: {}, password={}: {}", login, password, e);
        }
        return false;
    }
}
