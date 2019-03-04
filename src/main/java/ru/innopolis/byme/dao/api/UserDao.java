package ru.innopolis.byme.dao.api;

import ru.innopolis.byme.entity.User;
import ru.innopolis.byme.exception.UserLoginAlreadyExistsException;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Optional;

public interface UserDao {

    void create(User user) throws UserLoginAlreadyExistsException;

    Optional<User> selectById(int id);

    Optional<User> selectByLogin(String login);

    void update(User user);

    void updatePass(User user);

    void delete(User user);

    Collection<User> getAllUsers();

    boolean exists(String login, String password);

    boolean exists(String login);

    DataSource getDataSource();
}