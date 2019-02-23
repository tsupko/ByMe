package ru.innopolis.byme.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import ru.innopolis.byme.entity.City;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Реализация интерфейса {@code CityDao} для работы с объектами {@code entity.City}.
 *
 * @author Kuzina Anastasia
 */
@Repository
public class CityDaoImpl implements CityDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityDaoImpl.class);
    private JdbcTemplate jdbcTemplate;

    public CityDaoImpl() {

    }

    @Autowired
    public CityDaoImpl(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * соответствующие названия полей в таблице city
     */
    private static final String CITY_ID = "id";
    private static final String CITY_NAME = "name";


    private static final String SELECT_CITY_BY_NAME = "Select * from city where name = ? ";

    @Override
    public Optional<City> selectByName(String name) {
        if (name.trim().isEmpty()) {
            LOGGER.error("Некорректное название города name={}: {}", name);
            return Optional.empty ();
        }
        LOGGER.debug("Выбор города по name={}: {}", name);
        City city = new City();
        this.jdbcTemplate.execute(SELECT_CITY_BY_NAME, (PreparedStatementCallback<City>) stmt -> {
            stmt.setString(1,name);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    city.setId(rs.getInt(CITY_ID));
                    city.setName(rs.getString(CITY_NAME));
                    LOGGER.info("Город выбран успешно по name={}: {}. Инфо: {}", name, city.toString());
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при выборе города: ", e);
            }
            return city;
        });
        return Optional.of(city);
    }

    private static final String SELECT_CITY_BY_ID = "Select * from city where id = ? ";

    @Override
    public Optional<City> selectById(int id) {
        if (id <= 0) {
            LOGGER.error("Некорректный id={}: {}", id);
            return Optional.empty ();
        }
        LOGGER.debug("Выбор города по id={}: {}", id);
        City city = new City();
        this.jdbcTemplate.execute(SELECT_CITY_BY_ID, (PreparedStatementCallback<City>) stmt -> {
            stmt.setInt(1,id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    city.setId(rs.getInt(CITY_ID));
                    city.setName(rs.getString(CITY_NAME));
                    LOGGER.info("Город выбран успешно по id={}: {}. Инфо: {}", id, city.toString());
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при выборе города: ", e);
            }
            return city;
        });
        return Optional.of(city);
    }

    private static final String SELECT_ALL_CITIES = "Select * from city";

    @Override
    public Collection<City> getAllCities() {
        Collection<City> cities = new ArrayList<>();
        this.jdbcTemplate.execute(SELECT_ALL_CITIES, (PreparedStatementCallback<Collection<City>>) stmt -> {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    City city = new City();
                    city.setId(rs.getInt(CITY_ID));
                    city.setName(rs.getString(CITY_NAME));
                    LOGGER.info(city.toString());
                    cities.add(city);
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при получении всех городов из таблицы city ", e);
            }
            LOGGER.debug(cities.toString());
            return (cities);
        });
        return (cities);
    }
}
