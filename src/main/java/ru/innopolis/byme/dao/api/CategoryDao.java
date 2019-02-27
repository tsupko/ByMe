package ru.innopolis.byme.dao.api;

import ru.innopolis.byme.entity.Category;

import java.util.Collection;

public interface CategoryDao {
    Collection<Category> getAll();
}
