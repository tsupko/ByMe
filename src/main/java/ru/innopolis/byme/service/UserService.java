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
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final PasswordEncoder encoder;
    private final UserDao userDao;

    @Autowired
    public UserService(PasswordEncoder encoder, UserDao userDao) {
        LOGGER.debug("создали UserService");
        this.encoder = encoder;
        this.userDao = userDao;
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

    public User selectByLogin(String login) {
        return userDao.selectByLogin(login).get();
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
