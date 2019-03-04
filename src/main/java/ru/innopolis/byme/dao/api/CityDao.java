package ru.innopolis.byme.dao.api;

import ru.innopolis.byme.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityDao {

    Optional<City> selectByName(String name);

    Optional<City> selectById(int id);

    List<City> getAllCities();
}
