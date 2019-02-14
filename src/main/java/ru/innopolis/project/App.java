package ru.innopolis.project;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.innopolis.project.dao.UserDAO;
import ru.innopolis.project.dao.UserDAOImpl;

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

        UserDAO userDao = context.getBean("userDAOImpl", UserDAOImpl.class);

        for (int i = 0; i < 10; i++) {
            userDao.selectById(i);
        }

        context.close();
    }
}
