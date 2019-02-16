package ru.innopolis.project.servlet;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.core.io.ClassPathResource;
import ru.innopolis.project.dao.UserDao;
import ru.innopolis.project.dao.UserDaoImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {

    private final BasicDataSource dataSource = new BasicDataSource();
    private UserDao userDao;

    @Override
    public void init() {
        Properties properties = new Properties();

        try {
            properties.load(new ClassPathResource("jdbc.properties").getInputStream());
            String dbUrl =              properties.getProperty("jdbc.url");
            String dbUsername =         properties.getProperty("jdbc.username");
            String dbPassword =         properties.getProperty("jdbc.password");
            String driverClassName =    properties.getProperty("jdbc.driverClassName");

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
        resp.sendRedirect(req.getContextPath() + "/jsp/authorization.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("login");
        String password = req.getParameter("password");

        if (userDao.exists(name, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", name);

            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            resp.sendRedirect(req.getContextPath() + "/authorization");
        }
    }
}
