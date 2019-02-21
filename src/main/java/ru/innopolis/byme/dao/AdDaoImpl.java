package ru.innopolis.byme.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import ru.innopolis.byme.entity.Ad;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Реализация интерфейса {@code AdDao} для работы с объектами {@code entity.Ad}.
 *
 * @author Kuzina Anastasia
 */
@Repository
public class AdDaoImpl implements AdDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdDaoImpl.class);
    private JdbcTemplate jdbcTemplate;

    public AdDaoImpl() {

    }

    @Autowired
    public AdDaoImpl(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
     * sql-скрипт для выборки объявления по id
     */
    private static final String SELECT_AD_BY_ID = "select * from ad" +
            " where id = ? and is_actual = true";

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
        this.jdbcTemplate.execute(SELECT_AD_BY_ID, (PreparedStatementCallback<Ad>) stmt -> {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    assignResultSetToAdFields(rs, ad);
                }
                if (ad.getId() != 0) {
                    LOGGER.info("Объявление {} выбрано по id={} успешно", ad, id);
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при получении объявления по id={}: {}", id, e);
            }
            LOGGER.info("Объявление {} выбрано по id={} успешно", ad, id);
            return ad.getId() == 0 ? null : ad;
        });
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
        this.jdbcTemplate.execute(INSERT_AD, (PreparedStatementCallback<Ad>) stmt -> {
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
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при возвращении id после создания объявления: {}", e);
            }
            LOGGER.info("Объявление с id={} создано успешно", ad.getId());
            return ad;
        });
    }

    /**
     * sql-скрипт для изменения значений в таблице ad
     */
    private static final String UPDATE_AD = "update ad " +
            " set title = ?, text = ?, user_id = ?, category_id = ?, " +
            " price = ?, price_min = ?, confirm = ?, is_actual = ?\n" +
            " where id = ?\n";

    /**
     * изменение значений  в таблице ad
     * в соответствии с полями переданного экземпляра
     *
     * @param ad объект, для которого будет обновлена запись в БД
     */
    @Override
    public void update(Ad ad) {
        this.jdbcTemplate.execute(UPDATE_AD, (PreparedStatementCallback<Boolean>) stmt -> {
            stmt.setString(1, ad.getTitle());
            stmt.setString(2, ad.getText());
            stmt.setInt(3, ad.getUserId());
            stmt.setInt(4, ad.getCategoryId());
            stmt.setBigDecimal(5, ad.getPrice());
            stmt.setBigDecimal(6, ad.getPriceMin());
            stmt.setBoolean(7, ad.isConfirm());
            stmt.setBoolean(8, ad.isActual());
            stmt.setInt(9, ad.getId());
            stmt.execute();
            LOGGER.info("Объявление с id={} изменено успешно. Инфо: {}", ad.getId(), ad.toString());
            return true;
        });

    }

    /**
     * sql-скрипт для удаления значений в таблице ad
     */
    private static final String DELETE_AD = "update ad" +
            " set is_actual = false where id = ?\n";

    /**
     * Метод помечает запись в таблице ad как неактуальную
     *
     * @param ad объект, для которого запись в БД будет помечена как неактуальная
     */
    @Override
    public void delete(Ad ad) {
        this.jdbcTemplate.execute(DELETE_AD, (PreparedStatementCallback<Boolean>) stmt -> {
            stmt.setInt(1, ad.getId());
            stmt.execute();
            LOGGER.info("Объявление с id={} удалено успешно. Инфо: {}", ad.getId(), ad.toString());
            return true;
        });
    }

    /**
     * sql-скрипт для выбора всех объявлений из таблицы ad
     */
    private static final String SELECT_ALL_ADS = "Select * from ad where is_actual = true";

    /**
     * выбор всех объявлений из таблицы ad
     */
    @Override
    public Collection<Ad> getAll() {
        LOGGER.info("getAllAds");
        Collection<Ad> ads = new ArrayList<>();
        this.jdbcTemplate.execute(SELECT_ALL_ADS, (PreparedStatementCallback<Collection<Ad>>) stmt -> {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ad ad = new Ad();
                    assignResultSetToAdFields(rs, ad);
                    LOGGER.info(ad.toString());
                    ads.add(ad);
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при получении всех объявлений из таблицы ad ", e);
            }
            LOGGER.info(ads.toString());
            return (ads);
        });
        return (ads);
    }

    private void assignResultSetToAdFields(ResultSet rs, Ad ad) throws SQLException {
        ad.setId(rs.getInt(AD_ID));
        ad.setTitle(rs.getString(AD_TITLE));
        ad.setText(rs.getString(AD_TEXT));
        ad.setUserId(rs.getInt(AD_USER_ID));
        ad.setCategoryId(rs.getInt(AD_CATEGORY_ID));
        ad.setPrice(rs.getBigDecimal(AD_PRICE));
        ad.setPriceMin(rs.getBigDecimal(AD_PRICE_MIN));
        ad.setConfirm(rs.getBoolean(AD_CONFIRM));
        ad.setActual(rs.getBoolean(AD_IS_ACTUAL));
    }
}
