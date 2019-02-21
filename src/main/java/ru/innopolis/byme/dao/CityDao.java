package ru.innopolis.byme.dao;

import ru.innopolis.byme.entity.City;

import java.util.Collection;
import java.util.Optional;

public interface CityDao {

    Optional<City> selectByName(String name);

    Optional<City> selectById(int id);

    Collection<City> getAllCities();
}
