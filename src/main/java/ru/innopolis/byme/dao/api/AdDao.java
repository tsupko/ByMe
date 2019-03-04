package ru.innopolis.byme.dao.api;

import ru.innopolis.byme.entity.Ad;

import java.util.Collection;
import java.util.List;

public interface AdDao {

    Ad selectById(int id);

    Collection<Ad> selectByUserId(int userId);

    void create(Ad ad);

    void update(Ad ad);

    void delete(Ad ad);

    List<Ad> getAdvs(int maxAdvertsNumber, int categoryId, int cityId);
}
