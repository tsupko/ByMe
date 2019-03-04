package ru.innopolis.byme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.byme.dao.api.CityDao;
import ru.innopolis.byme.entity.City;
import ru.innopolis.byme.entity.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityDao cityDao;

    public List<City> getAll(){
        return cityDao.getAllCities();
    }

    public City selectByUser (User user){
        Optional<City> city = cityDao.selectById(user.getCityId());
        return city.orElse(null);
    }

    public List<City> getCityListWithSelected(int cityId) {
        List<City> cityList = getCityList();
        ((LinkedList<City>) cityList).addFirst(new City(0, "Any Location"));

        for (City city : cityList) {
            if (city.getId() == cityId) {
                ((LinkedList<City>) cityList).addFirst(city);
                break;
            }
        }
        return cityList;
    }

    public List<City> getCityList() {
        return new LinkedList<>(cityDao.getAllCities());
    }

}
