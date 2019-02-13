package ru.inno.project.dao;

import org.apache.log4j.Logger;
import ru.inno.project.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * реализация UserDAO
 * для таблицы user, имеет POJO реализациею entity.User
 *
 * @author Kuzina Anastasia
 */
public class UserDAOImpl implements UserDAO {

    public static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);
    private final Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
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
    private static final String USER_IS_ACTYAL = "is_actual";

    /**
     *  sql-скрипт для создания записи в соответствующей таблице
     */
    private static final String INSERT_USER = "insert into \"user\"" +
            " (login, password, name, email, phone_number, role_id, city_id, is_actual)" +
            " values (?, ?, ?, ?, ?, ?, ?, ?) returning id";

    /**
     * создание записи в БД с полями переданного экземпляра
     *  в соответствующей таблице
     *
     * @param user объект для которого будет создана запись в БД
     */
    @Override
    public void create(User user) {
        LOGGER.debug("Creating user  " + user.toString());
        System.out.println("Creating user  " + user.toString());
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_USER)) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhone_number());
            stmt.setInt(6, user.getRole_id());
            stmt.setInt(7, user.getCity_id());
            stmt.setBoolean(8, user.is_actual());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    user.setId(rs.getInt("id"));
                    LOGGER.debug("User with id=" + user.getId() + " created");
                    System.out.println(("User with id=" + user.getId() + " created"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *  sql-скрипт для выборки по id
     */
    private static final String SELECT_USER_BY_ID = "select * from \"user\" where id = ?";

    /**
     * создание объекта user по переданному id
     *
     * @param id первичный ключ записи в БД
     */
    @Override
    public User selectById(int id) {
        LOGGER.debug("selectById for id = " + id);
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
                    user.setPhone_number(rs.getString(USER_PHONE_NUMBER));
                    user.setRole_id(rs.getInt(USER_ROLE_ID));
                    user.setCity_id(rs.getInt(USER_CITY_ID));
                    user.setActual(rs.getBoolean(USER_IS_ACTYAL));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOGGER.info(user.toString());
        return (user);
    }
}
