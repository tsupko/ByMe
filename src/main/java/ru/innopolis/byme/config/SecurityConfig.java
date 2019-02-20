package ru.innopolis.byme.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    private final PasswordEncoder encoder;
    private final DataSource dataSource;

    public SecurityConfig(PasswordEncoder encoder, DataSource dataSource) {
        this.encoder = encoder;
        this.dataSource = dataSource;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        LOGGER.info("попытка идентифицировать пользователя");
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT login, password, true as enabled FROM public.user WHERE login = ?")
                .authoritiesByUsernameQuery("SELECT login, 'USER' FROM public.user WHERE login = ?")
                .passwordEncoder(encoder);
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
//                .loginPage("/authorization")
                .usernameParameter("login").passwordParameter("password")
                .permitAll()
                .and()
            .logout().logoutSuccessUrl("/")
                .permitAll();
    }
}
