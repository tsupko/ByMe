package ru.innopolis.byme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.byme.dao.api.CategoryDao;
import ru.innopolis.byme.entity.Category;
import ru.innopolis.byme.transfer.CategoryTree;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    public Collection<Category> getAll(){
        return categoryDao.getAll();
    }

    public Category getCategory(int id){
        return categoryDao.selectById(id);
    }

    public List<CategoryTree> getCategoryListWithSelected(int categoryId) {
        List<CategoryTree> categoryTreeList = getCategoryList();
        ((LinkedList<CategoryTree>) categoryTreeList).addFirst(new CategoryTree(0, "Any category", 0));

        Iterator<CategoryTree> categoryTreeIterator = categoryTreeList.iterator();
        while (categoryTreeIterator.hasNext()) {
            CategoryTree category = categoryTreeIterator.next();
            if (category.getId() == categoryId) {
                CategoryTree currentCategory = category;
                ((LinkedList<CategoryTree>) categoryTreeList).addFirst(currentCategory);
                break;
            }
        }
        return categoryTreeList;
    }

    private List<CategoryTree> getCategoryList() {
        return CategoryTree.categoryListToTree(new LinkedList<>(categoryDao.getAll()));
    }

}
