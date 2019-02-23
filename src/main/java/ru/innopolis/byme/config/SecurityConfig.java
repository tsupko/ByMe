package ru.innopolis.byme.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.innopolis.byme.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    // TODO: 2019-02-21 реализовать данные запросы в DAO
    private static final String USER_BY_USER = "SELECT login, password, true as enabled FROM public.user WHERE login = ?";
    private static final String USER_BT_ROLE = "SELECT login, 'USER' FROM public.user WHERE login = ?";

    private final UserService service;

    public SecurityConfig(UserService service) {
        this.service = service;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        LOGGER.info("попытка идентифицировать пользователя");
        auth
                .jdbcAuthentication()
                .dataSource(service.getDataSource())
                .usersByUsernameQuery(USER_BY_USER)
                .authoritiesByUsernameQuery(USER_BT_ROLE)
                .passwordEncoder(service.getEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.info("начали проверку безопасности");
        http
                .csrf().disable()
            .authorizeRequests()
                .antMatchers("/", "/registration")
                .permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .usernameParameter("login").passwordParameter("password")
                .permitAll()
                .and()
            .logout().logoutSuccessUrl("/")
                .permitAll();
    }
}
