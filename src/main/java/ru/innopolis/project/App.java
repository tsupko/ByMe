package ru.innopolis.project;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.innopolis.project.dao.UserDao;
import ru.innopolis.project.dao.UserDaoImpl;

/**
 * Клиентский код.
 */
@Configuration
@ComponentScan
public class App {
    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        UserDao userDao = context.getBean("userDaoImpl", UserDaoImpl.class);

        for (int i = 0; i < 10; i++) {
            userDao.selectById(i);
        }

        context.close();
    }
}
