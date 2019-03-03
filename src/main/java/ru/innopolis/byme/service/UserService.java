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
import ru.innopolis.byme.entity.City;
import ru.innopolis.byme.entity.User;
import ru.innopolis.byme.exception.UserLoginAlreadyExistsException;
import ru.innopolis.byme.transfer.CategoryTree;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
        LOGGER.debug("создали UserService");
        this.encoder = encoder;
        this.userDao = userDao;
        this.cityDao = cityDao;
        this.categoryDao = categoryDao;
    }

    public void saveUser(User user) throws UserLoginAlreadyExistsException {
        LOGGER.debug("регистрируем нового пользователя в БД");
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.create(user);
    }

    public void changePasswUser(User user) {
        LOGGER.debug("Изменение пароля для пользователя " + user);
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.updatePass(user);
    }

    public User newUser() {
        LOGGER.debug("создаем пользователя");
        return new User();
    }

    public PasswordEncoder getEncoder() {
        LOGGER.debug("запрос на получение кодировки");
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

        for (City city : cityList) {
            if (city.getId() == cityId) {
                ((LinkedList<City>) cityList).addFirst(city);
                break;
            }
        }
        return cityList;
    }

    public List<CategoryTree> getCategoryListWithSelected(int categoryId) {
        List<CategoryTree> categoryTreeList = getCategoryList();
        ((LinkedList<CategoryTree>) categoryTreeList).addFirst(new CategoryTree(0, "Any category", 0));

        for (CategoryTree category : categoryTreeList) {
            if (category.getId() == categoryId) {
                ((LinkedList<CategoryTree>) categoryTreeList).addFirst(category);
                break;
            }
        }
        return categoryTreeList;
    }

    private List<CategoryTree> getCategoryList() {
        return CategoryTree.categoryListToTree(new LinkedList<>(categoryDao.getAll()));
    }

    public List<City> getCityList() {
        return new LinkedList<>(cityDao.getAllCities());
    }

    public User selectByLogin(String login) {
        Optional<User> user = userDao.selectByLogin(login);
        if (user.isPresent()){
            return user.get();
        }
        LOGGER.error("NPE in selectByLogin of {} with params {}", this.getClass().getSimpleName(), login);
        throw new NullPointerException();
    }

    public User selectById(int id) {
        Optional<User> user = userDao.selectById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            LOGGER.error("NPE in selectById with params:{}", id);
            throw new NullPointerException();
        }
    }

    public void update(User user) {
        userDao.update(user);
    }
}
