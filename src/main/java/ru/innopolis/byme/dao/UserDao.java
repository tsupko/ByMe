package ru.innopolis.byme.dao;

import ru.innopolis.byme.entity.User;

public interface UserDao {

    void create(User user);

    User selectById(int id);

    public boolean exists(String login, String password);
}