package ru.innopolis.byme.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.innopolis.byme.dao.UserDao;
import ru.innopolis.byme.entity.User;
import ru.innopolis.byme.exception.UserLoginAlreadyExistsException;

import javax.sql.DataSource;

@Component
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final PasswordEncoder encoder;
    private final UserDao dao;

    @Autowired
    public UserService(PasswordEncoder encoder, UserDao dao) {
        LOGGER.info("создали UserService");
        this.encoder = encoder;
        this.dao = dao;
    }

    public void saveUser(User user) throws UserLoginAlreadyExistsException {
        LOGGER.info("регистрируем нового пользователя в БД");
        user.setPassword(encoder.encode(user.getPassword()));
        dao.create(user);
    }

    public User newUser(){
        LOGGER.info("создаем пользователя");
        return new User();
    }

    public PasswordEncoder getEncoder(){
        LOGGER.info("запрос на получение кодировки");
        return encoder;
    }

    public DataSource getDataSource(){
        return dao.getDataSource();
    }


}
