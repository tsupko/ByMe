package ru.innopolis.byme.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.byme.entity.Ad;

import java.sql.*;
import java.util.Collection;

/**
 * Реализация интерфейса {@code AdDao} для работы с объектами {@code entity.Ad}.
 *
 * @author Kuzina Anastasia
 */
@Repository
public class AdDaoImpl implements AdDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdDaoImpl.class);
    private Connection connection = null;
    private JdbcTemplate dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AdDaoImpl() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/byme",
                    "postgres",
                    ""
            );
        } catch (SQLException e) {
            LOGGER.error("Исключение при установке соединения с базой данных: {}", e);
        }
    }

    public AdDaoImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * соответствующие названия полей в таблице ad
     */
    private static final String AD_ID = "id";
    private static final String AD_TITLE = "title";
    private static final String AD_TEXT = "text";
    private static final String AD_USER_ID = "user_id";
    private static final String AD_CATEGORY_ID = "category_id";
    private static final String AD_PRICE = "price";
    private static final String AD_PRICE_MIN = "price_min";
    private static final String AD_CONFIRM = "confirm";
    private static final String AD_IS_ACTUAL = "is_actual";

    /**
     * sql-скрипт для выборки по id
     */
    private static final String SELECT_AD_BY_ID = "select * from ad where id = ?";

    /**
     * создание объекта ad по переданному id
     *
     * @param id первичный ключ записи в БД
     */
    @Override
    public Ad selectById(int id) {
        if (id <= 0) {
            LOGGER.error("Некорректный id={}: {}", id);
            return null;
        }
        LOGGER.debug("Выбор объявления по id={}", id);
        Ad ad = new Ad();
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_AD_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ad.setId(id);
                    ad.setTitle(rs.getString(AD_TITLE));
                    ad.setText(rs.getString(AD_TEXT));
                    ad.setUserId(rs.getInt(AD_USER_ID));
                    ad.setCategoryId(rs.getInt(AD_CATEGORY_ID));
                    ad.setPrice(rs.getBigDecimal(AD_PRICE));
                    ad.setPriceMin(rs.getBigDecimal(AD_PRICE_MIN));
                    ad.setConfirm(rs.getBoolean(AD_CONFIRM));
                    ad.setActual(rs.getBoolean(AD_IS_ACTUAL));
                }
                if (ad.getId() != 0) {
                    LOGGER.info("{} выбран по id={} успешно", ad, id);
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при получении объявления по id={}: {}", id, e);
            }
        } catch (SQLException e) {
            LOGGER.error("Исключение при подготовке запроса на выбор объявления по id={}: {}", id, e);
        }
        return ad.getId() == 0 ? null : ad;
    }

    /**
     * sql-скрипт для создания записи в соответствующей таблице
     */
    private static final String INSERT_AD = "insert into ad" +
            " ( title, text, user_id, category_id, price, price_min, confirm, is_actual)\n" +
            " values (?,?,?,?,?,?,?,?) returning id";

    /**
     * создание записи в БД с полями переданного экземпляра
     * в соответствующей таблице
     *
     * @param ad объект, для которого будет создана запись в БД
     */
    @Override
    public void create(Ad ad) {
        if (ad == null) {
            LOGGER.error("Попытка создавать запись в БД для ad == null ");
            return;
        }
        LOGGER.debug("Создание объявления {}", ad);
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_AD)) {
            stmt.setString(1, ad.getTitle());
            stmt.setString(2, ad.getText());
            stmt.setInt(3, ad.getUserId());
            stmt.setInt(4, ad.getCategoryId());
            stmt.setBigDecimal(5, ad.getPrice());
            stmt.setBigDecimal(6, ad.getPriceMin());
            stmt.setBoolean(7, ad.isConfirm());
            stmt.setBoolean(8, ad.isActual());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ad.setId(rs.getInt("id"));
                    LOGGER.info("Объявление с id={} создано успешно", ad.getId());
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при возвращении id после создания объявления: {}", e);
            }
        } catch (SQLException e) {
            LOGGER.error("Исключение при подготовке insert-запроса: {}", e);
        }
    }

    @Override
    public void update(Ad ad) {

    }

    @Override
    public void delete(Ad ad) {

    }

    @Override
    public Collection<Ad> getAll() {
        return null;
    }
}
