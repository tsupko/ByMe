package ru.innopolis.byme.dao;

import ru.innopolis.byme.entity.Ad;

import java.util.Collection;

public interface AdDao {

    Ad selectById(int id);

    Collection<Ad> selectByUserId(int userId);

    void create(Ad ad);

    void update(Ad ad);

    void delete(Ad ad);

    Collection<Ad> getAll();

}
