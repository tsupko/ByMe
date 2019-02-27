package ru.innopolis.byme.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * POJO класс для таблицы category
 *
 * @author Kuzina Anastasia
 */
@Getter
@Setter
public class Category {

    private int id;
    private String name;
    private int parentId;

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


