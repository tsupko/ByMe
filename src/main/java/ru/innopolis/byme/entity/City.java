package ru.innopolis.byme.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * POJO класс для таблицы city
 *
 * @author Kuzina Anastasia
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id &&
                name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
