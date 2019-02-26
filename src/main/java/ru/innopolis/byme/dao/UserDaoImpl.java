package ru.innopolis.byme.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import ru.innopolis.byme.entity.User;
import ru.innopolis.byme.exception.UserLoginAlreadyExistsException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Реализация интерфейса {@code UserDao} для работы с объектами {@code entity.User}.
 *
 * @author Kuzina Anastasia, Александр Цупко
 */
@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    private JdbcTemplate jdbcTemplate;

    public UserDaoImpl() {

    }

    @Autowired
    public UserDaoImpl(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
    public void create(User user) throws UserLoginAlreadyExistsException {
        if (user == null) {
            LOGGER.error("Попытка создавать запись в БД для user == null ");
            return;
        }
        if (exists(user.getLogin())) {
            throw new UserLoginAlreadyExistsException("Пользователь c данным логином уже зарегистрирован: login = " + user.getLogin());
        } else {
            LOGGER.debug("Создание пользователя {}", user);
            this.jdbcTemplate.execute(INSERT_USER, (PreparedStatementCallback<User>) stmt -> {
                stmt.setString(1, user.getLogin());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getName());
                stmt.setString(4, user.getEmail());
                stmt.setString(5, user.getPhoneNumber());
                stmt.setInt(6, 3 /* role=user */);
                stmt.setInt(7, 1 /* city=Kazan */);
                stmt.setBoolean(8, true /* is_actual */);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        user.setId(rs.getInt("id"));
                        LOGGER.info("Пользователь с id={} создан успешно. Инфо: {}", user.getId(), user.toString());
                    }
                } catch (SQLException e) {
                    LOGGER.error("Исключение при создании пользователя");
                    throw new RuntimeException(e);
                }
                return user;
            });
        }
    }

    /**
     * sql-скрипт для выборки по id
     */
    private static final String SELECT_USER_BY_ID = "select * from public.user" +
            " where is_actual = true and id = ?  ";

    /**
     * создание объекта user по переданному id
     *
     * @param id первичный ключ записи в БД
     */
    @Override
    public Optional<User> selectById(int id) {
        if (id <= 0) {
            LOGGER.error("Некорректный id={}", id);
            return Optional.empty();
        }
        LOGGER.debug("Выбор пользователя по id={}", id);
        User user = new User();
        this.jdbcTemplate.execute(SELECT_USER_BY_ID, (PreparedStatementCallback<User>) stmt -> {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    assignResultSetToUserFields(user, rs);
                    LOGGER.info("Пользователь с id={} выбран успешно. Инфо: {}", user.getId(), user.toString());
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при выборе пользователя: ", e);
            }
            return user;
        });
        return Optional.of(user);
    }

    /**
     * sql-скрипт для выборки пользователя по login
     */
    private static final String SELECT_USER_BY_LOGIN = "select * from public.user" +
            " where login = ? and is_actual = true ";

    /**
     * создание объекта user по переданному login
     *
     * @param login
     */
    @Override
    public Optional<User> selectByLogin(String login) {
        if (login.trim().isEmpty()) {
            LOGGER.error("Некорректный login={}", login);
            return Optional.empty();
        }
        LOGGER.debug("Выбор пользователя по login={}", login);
        User user = new User();
        this.jdbcTemplate.execute(SELECT_USER_BY_LOGIN, (PreparedStatementCallback<User>) stmt -> {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    assignResultSetToUserFields(user, rs);
                    LOGGER.info("Пользователь по login={} выбран успешно. Инфо: {}", user.getLogin(), user.toString());
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при выборе пользователя: ", e);
            }
            return user;
        });
        return Optional.of(user);
    }

    /**
     * sql-скрипт для изменения значений в таблице user
     */
    private static final String UPDATE_USER = "update public.user" +
            " set password = ?, name = ?, email = ?, phone_number = ? where id = ?\n";

    /**
     * изменение значений  в таблице user
     * в соответствии с полями переданного экземпляра
     *
     * @param user объект, для которого будет обновлена запись в БД
     */
    @Override
    public void update(User user) {
        this.jdbcTemplate.execute(UPDATE_USER, (PreparedStatementCallback<Boolean>) stmt -> {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setInt(5, user.getId());
            stmt.execute();
            LOGGER.info("Пользователь с id={} изменен успешно. Инфо: {}", user.getId(), user.toString());
            return true;
        });
    }

    /**
     * sql-скрипт для удаления значений в таблице user
     */
    private static final String DELETE_USER = "update public.user" +
            " set is_actual = false where id = ?\n";

    /**
     * Метод помечает запись в таблице user как неактуальную
     *
     * @param user объект, для которого запись в БД будет помечена как неактуальная
     */
    @Override
    public void delete(User user) {
        this.jdbcTemplate.execute(DELETE_USER, (PreparedStatementCallback<Boolean>) stmt -> {
            stmt.setInt(1, user.getId());
            stmt.execute();
            LOGGER.info("Пользователь с id={} удален успешно. Инфо: {}", user.getId(), user.toString());
            return true;
        });
    }

    /**
     * sql-скрипт для выбора всех актуальных пользователей из таблицы user
     */
    private static final String SELECT_ALL_USERS = "Select * from public.user where is_actual = true";

    /**
     * выбор всех актуальных пользователей из таблицы user
     */
    @Override
    public Collection<User> getAllUsers() {
        LOGGER.debug("getAllUsers");
        Collection<User> users = new ArrayList<>();
        this.jdbcTemplate.execute(SELECT_ALL_USERS, (PreparedStatementCallback<Collection<User>>) stmt -> {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    assignResultSetToUserFields(user, rs);
                    LOGGER.info(user.toString());
                    users.add(user);
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при получении всех пользователей из таблицы user ", e);
            }
            LOGGER.debug(users.toString());
            return (users);
        });
        return (users);
    }

    /**
     * sql-скрипт для проверки наличия актуального пользователя в БД
     */
    private static final String SELECT_BY_LOGIN_PASS = "SELECT * FROM public.user" +
            " WHERE login = ? AND password = ? AND is_actual = true";

    /**
     * проверка наличия актуального пользователя с указанными параметрами в таблице user
     *
     * @param login    логин пользователся
     * @param password пароль пользователся
     */
    @Override
    public boolean exists(String login, String password) {
        if (login.trim().isEmpty() || password.trim().isEmpty()) {
            LOGGER.error("Параметры login и password не могут быть пустыми");
            return false;
        }
        Boolean exists = this.jdbcTemplate.execute(SELECT_BY_LOGIN_PASS, (PreparedStatementCallback<Boolean>) stmt -> {
            stmt.setString(1, login);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при проверке наличия пользователя по login={} , password={}",
                        login, password, e);
            }
            return false;
        });
        return exists;
    }

    /**
     * sql-скрипт для проверки наличия актуального пользователя в БД
     */
    private static final String SELECT_BY_LOGIN = "SELECT * FROM public.user" +
            " WHERE login = ? AND is_actual = true";

    /**
     * проверка наличия актуального пользователя с указанными параметрами в таблице user
     *
     * @param login логин пользователся
     */
    @Override
    public boolean exists(String login) {
        if (login.trim().isEmpty()) {
            LOGGER.error("Параметр login не может быть пустым");
            return false;
        }
        Boolean exists = this.jdbcTemplate.execute(SELECT_BY_LOGIN, (PreparedStatementCallback<Boolean>) stmt -> {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при проверке наличия пользователя по login={}: {}", login, e);
            }
            return false;
        });
        return exists;
    }

    @Override
    public DataSource getDataSource() {
        return jdbcTemplate.getDataSource();
    }

    /**
     * присвоение полям объекта user
     * значений из результата выполнения SQL-запроса
     *
     * @param user      объект, полям которого будут присвоены значения из resultSet
     * @param resultSet результат выполнения SQL-запроса
     */
    private void assignResultSetToUserFields(User user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getInt(USER_ID));
        user.setLogin(resultSet.getString(USER_LOGIN));
        user.setPassword(resultSet.getString(USER_PASSWORD));
        user.setName("\"" + resultSet.getString(USER_NAME) + "\"");
        user.setEmail("\"" + resultSet.getString(USER_EMAIL) + "\"");
        user.setPhoneNumber("\"" + resultSet.getString(USER_PHONE_NUMBER) + "\"");
        user.setRoleId(resultSet.getInt(USER_ROLE_ID));
        user.setCityId(resultSet.getInt(USER_CITY_ID));
        user.setActual(resultSet.getBoolean(USER_IS_ACTUAL));
    }
}
