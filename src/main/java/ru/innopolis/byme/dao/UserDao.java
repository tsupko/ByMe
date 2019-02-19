package ru.innopolis.byme.dao;

import ru.innopolis.byme.entity.User;
import java.util.Collection;
import java.util.Optional;

public interface UserDao {

    void create(User user);

    Optional<User> selectById(int id);

    void update(User user);

    void delete(User user);

    Collection<User> getAllUsers();

    boolean exists(String login, String password);
}