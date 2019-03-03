package ru.innopolis.byme.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.innopolis.byme.dao.api.UserDao;
import ru.innopolis.byme.entity.User;
import ru.innopolis.byme.exception.UserLoginAlreadyExistsException;

import javax.sql.DataSource;
import java.util.Optional;

/**
 * слой сервиса для User Controller
 * реализует логику работы с пользователем
 *
 * @author Komovskiy Dmitriy
 * @since 21.02.2019
 */
@Component
public class MainService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainService.class);

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserDao userDao;

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
