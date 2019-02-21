package ru.innopolis.byme.dao;

import ru.innopolis.byme.entity.Category;

import java.util.Collection;

public interface CategoryDao {
    Collection<Category> getAll();
}
