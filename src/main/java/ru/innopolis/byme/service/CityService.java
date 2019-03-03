package ru.innopolis.byme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.byme.dao.api.CityDao;
import ru.innopolis.byme.entity.City;
import ru.innopolis.byme.entity.User;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityDao cityDao;

    public List<City> getAll(){
        return cityDao.getAllCities();
    }

    public City selectByUser (User user){
        return  cityDao.selectById(user.getCityId()).get();
    }

}
