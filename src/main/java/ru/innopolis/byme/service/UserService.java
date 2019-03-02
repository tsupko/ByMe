package ru.innopolis.byme.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.innopolis.byme.dao.api.AdDao;
import ru.innopolis.byme.dao.api.CategoryDao;
import ru.innopolis.byme.dao.api.CityDao;
import ru.innopolis.byme.dao.api.UserDao;
import ru.innopolis.byme.entity.Ad;
import ru.innopolis.byme.entity.Category;
import ru.innopolis.byme.entity.City;
import ru.innopolis.byme.entity.User;
import ru.innopolis.byme.exception.UserLoginAlreadyExistsException;
import ru.innopolis.byme.transfer.CategoryTree;

import javax.sql.DataSource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * слой сервиса для User Controller
 * реализует логику работы с пользователем
 *
 * @author Komovskiy Dmitriy
 * @since 21.02.2019
 */
@Component
public class UserService {

    @Autowired
    private AdDao adDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final PasswordEncoder encoder;
    private final UserDao userDao;
    private final CityDao cityDao;
    private final CategoryDao categoryDao;

    @Autowired
    public UserService(PasswordEncoder encoder, UserDao userDao, CityDao cityDao, CategoryDao categoryDao) {
        LOGGER.info("создали UserService");
        this.encoder = encoder;
        this.userDao = userDao;
        this.cityDao = cityDao;
        this.categoryDao = categoryDao;
    }

    public void saveUser(User user) throws UserLoginAlreadyExistsException {
        LOGGER.info("регистрируем нового пользователя в БД");
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.create(user);
    }

    public User newUser() {
        LOGGER.info("создаем пользователя");
        return new User();
    }

    public PasswordEncoder getEncoder() {
        LOGGER.info("запрос на получение кодировки");
        return encoder;
    }

    public DataSource getDataSource() {
        return userDao.getDataSource();
    }

    public List<Ad> getAdvs(int maxAdvertsNumber) {
        return adDao.getAdvs(maxAdvertsNumber, 0, 0);
    }

    public List<Ad> getAdvs(int maxAdvertsNumber, int categoryId, int cityId) {
        return adDao.getAdvs(maxAdvertsNumber, categoryId, cityId);
    }

    public List<City> getCityListWithSelected(int cityId) {
        List<City> cityList = getCityList();
        ((LinkedList<City>) cityList).addFirst(new City(0, "Any Location"));

        Iterator<City> cityIterator = cityList.iterator();
        while (cityIterator.hasNext()){
            City city = cityIterator.next();
            if(city.getId() == cityId){
                City currentCity = city;
                ((LinkedList<City>) cityList).addFirst(currentCity);
                break;
            }
        }
        return cityList;
    }

    public List<CategoryTree> getCategoryListWithSelected(int categoryId) {
        List<CategoryTree> categoryTreeList = getCategoryList();
        ((LinkedList<CategoryTree>) categoryTreeList).addFirst(new CategoryTree(0, "Any category", 0));

        Iterator<CategoryTree> categoryTreeIterator = categoryTreeList.iterator();
        while (categoryTreeIterator.hasNext()){
            CategoryTree category = categoryTreeIterator.next();
            if(category.getId() == categoryId){
                CategoryTree currentCategory = category;
                ((LinkedList<CategoryTree>) categoryTreeList).addFirst(currentCategory);
                break;
            }
        }
        return categoryTreeList;
    }

    public List<CategoryTree> getCategoryList() {
        return CategoryTree.categoryListToTree(new LinkedList<>(categoryDao.getAll()));
    }

    public List<City> getCityList() {
        return new LinkedList<>(cityDao.getAllCities());
    }

    public User selectByLogin(String login){
        return userDao.selectByLogin(login).get();
    }

    public User selectById(int id){
        return userDao.selectById(id).get();
    }
}
