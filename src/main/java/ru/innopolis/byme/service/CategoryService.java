package ru.innopolis.byme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.byme.dao.api.CategoryDao;
import ru.innopolis.byme.entity.Category;

import java.util.Collection;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    public Collection<Category> getAll(){
        return categoryDao.getAll();
    }
}
