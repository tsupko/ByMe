package ru.innopolis.byme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.byme.dao.api.CityDao;
import ru.innopolis.byme.entity.City;
import ru.innopolis.byme.entity.User;

import java.util.Iterator;
import java.util.LinkedList;
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

    public List<City> getCityListWithSelected(int cityId) {
        List<City> cityList = getCityList();
        ((LinkedList<City>) cityList).addFirst(new City(0, "Any Location"));

        Iterator<City> cityIterator = cityList.iterator();
        while (cityIterator.hasNext()) {
            City city = cityIterator.next();
            if (city.getId() == cityId) {
                City currentCity = city;
                ((LinkedList<City>) cityList).addFirst(currentCity);
                break;
            }
        }
        return cityList;
    }

    public List<City> getCityList() {
        return new LinkedList<>(cityDao.getAllCities());
    }

}
