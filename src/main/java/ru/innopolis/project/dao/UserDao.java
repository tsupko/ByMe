package ru.innopolis.project.dao;

import ru.innopolis.project.entity.User;

public interface UserDao {

    void create(User user);

    User selectById(int id);

    public boolean exists(String name, String password);
}