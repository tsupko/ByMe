package ru.inno.project.dao;

import ru.inno.project.entity.User;

public interface UserDAO {

    void create(User user);
    User selectById(int id);

}