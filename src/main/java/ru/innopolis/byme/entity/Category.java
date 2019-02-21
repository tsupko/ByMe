package ru.innopolis.byme.entity;

import java.util.Objects;

/**
 * POJO класс для таблицы category
 *
 * @author Kuzina Anastasia
 */
public class Category {

    private int id;
    private String name;
    private int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id &&
                parentId == category.parentId &&
                name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentId);
    }
}


