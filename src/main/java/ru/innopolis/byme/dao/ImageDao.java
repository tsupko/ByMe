package ru.innopolis.byme.dao;

import ru.innopolis.byme.entity.Image;

import java.util.List;

public interface ImageDao {
    void create(Image image);

    boolean exists(int adId);

    List<Image> getAll();
}
