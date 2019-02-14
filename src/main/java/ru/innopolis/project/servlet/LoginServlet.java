package ru.innopolis.project.servlet;


import org.apache.commons.dbcp.BasicDataSource;
import ru.innopolis.project.dao.UserDao;
import ru.innopolis.project.dao.UserDaoImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserDao userDao;

    BasicDataSource dataSource = new BasicDataSource();
    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            dataSource.setUsername(dbUsername);
            dataSource.setPassword(dbPassword);
            dataSource.setUrl(dbUrl);
            dataSource.setDriverClassName(driverClassName);

            userDao = new UserDaoImpl(dataSource);


        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (userDao.isExist(name, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", name);

            resp.sendRedirect(req.getContextPath() + "/home");
//            req.getServletContext().getRequestDispatcher("/home").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
