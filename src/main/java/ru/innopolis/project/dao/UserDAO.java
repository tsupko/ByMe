package ru.innopolis.project.dao;

import ru.innopolis.project.entity.User;

public interface UserDAO {
    void create(User user);
    User selectById(int id);
    public boolean isExist(String name, String password);
}