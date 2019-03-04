package ru.innopolis.byme.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import ru.innopolis.byme.dao.api.AdDao;
import ru.innopolis.byme.entity.Ad;
import ru.innopolis.byme.entity.Image;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    private static final String AD_DATE = "date";
    private static final String AD_DAY_COUNT = "day_count";

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
     * sql-скрипт для выбора объявлений по автору (user_id)
     */
    private static final String SELECT_AD_BY_USER_ID = "select * from ad" +
            " where user_id = ? and is_actual = true order by id desc ";

    /**
     * выбор объявлений из таблицы ad,
     * принадлежащих одному автору (user_id)
     *
     * @param userId внешний ключ для связи таблиц ad и user
     */
    @Override
    public Collection<Ad> selectByUserId(int userId) {
        Collection<Ad> ads = new ArrayList<>();
        this.jdbcTemplate.execute(SELECT_AD_BY_USER_ID, (PreparedStatementCallback<Collection<Ad>>) stmt -> {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ad ad = new Ad();
                    assignResultSetToAdFields(rs, ad);
                    LOGGER.debug(ad.toString());
                    ads.add(ad);
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при получении объявлений по автору user_id={}", userId, e);
            }
            LOGGER.debug(ads.toString());
            return ads;
        });
        return ads;
    }

    /**
     * sql-скрипт для создания записи в соответствующей таблице
     */
    private static final String INSERT_AD = "insert into ad" +
            " ( title, text, user_id, category_id, price, price_min, confirm, is_actual, date, day_count )\n" +
            " values (?,?,?,?,?,?,?,?,?,?) returning id";

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
            stmt.setDate(9, new Date(System.currentTimeMillis()));
            stmt.setInt(10, ad.getDayCount());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ad.setId(rs.getInt("id"));
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при возвращении id после создания объявления", e);
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
     * Метод для получения списка объявлений по заданным критериям
     *
     * @param maxAdvertsNumber ограничение на количество объявлений в списке
     * @param categoryId       значение выбранной пользователем категории товара
     * @param cityId           значение выбранного пользователем города
     *                         если значение какого-либо параметра равно 0, то фильтр
     *                         по данному критерию не используется
     */
    @Override
    public List<Ad> getAdvs(int maxAdvertsNumber, int categoryId, int cityId) {
        String sql = buildSqlForAdvs(maxAdvertsNumber, categoryId, cityId);
        List<Ad> result = new ArrayList<>();
        this.jdbcTemplate.execute(sql, (PreparedStatementCallback<List<Ad>>) stmt -> {
            if (cityId > 0) {
                stmt.setInt(1, cityId);
                if (categoryId > 0) {
                    stmt.setInt(2, categoryId);
                }
            } else if (categoryId > 0) {
                stmt.setInt(1, categoryId);
            }
            getAdvsFomPstmt(result, stmt);
            LOGGER.debug(result.toString());
            return (result);
        });
        return (result);
    }

    private String buildSqlForAdvs(int i, int categoryId, int cityId) {
        String sql = "select * from ad\n" +
                "  join image on image.ad_id=ad.id\n";
        if (cityId > 0) {
            sql += "  join public.user u on ad.user_id = u.id\n";
        }
        sql += " where ad.is_actual=true\n";
        if (cityId > 0) {
            sql += "      and city_id = ?\n";
        }
        if (categoryId > 0) {
            sql += "      and category_id in (\n" +
                    "         WITH RECURSIVE r AS (\n" +
                    "           SELECT id, parent_id, name, 1 AS level\n" +
                    "           FROM category\n" +
                    "           WHERE id = ?\n" +
                    "           UNION ALL\n" +
                    "           SELECT category.id, category.parent_id, category.name, r.level + 1 AS level\n" +
                    "           FROM category\n" +
                    "                  JOIN r ON category.parent_id = r.id\n" +
                    "           ) SELECT id FROM r\n" +
                    "     )  \n";
        }
        sql+=" order by ad.id desc";
        if (i != 0) {
            sql += " limit %d";
            sql = String.format(sql, i);
        }
        LOGGER.debug("sql =" + sql);
        return sql;
    }

    private void getAdvsFomPstmt(List<Ad> result, PreparedStatement stmt) {
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Ad ad = new Ad();
                Image image = new Image();
                assignResultSetToAdFields(rs, ad);
                assignResultSetToImageFields(rs, image);
                ad.setImage(image);
                result.add(ad);
            }
        } catch (SQLException e) {
            LOGGER.error("Исключение при получении списка объявлений из таблицы ad ", e);
        }
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
        ad.setDate(rs.getDate(AD_DATE));
        ad.setDayCount(rs.getInt(AD_DAY_COUNT));
    }

    private void assignResultSetToImageFields(ResultSet rs, Image image) throws SQLException {
        image.setId(rs.getInt("id"));
        image.setImg(rs.getString("img"));
        image.setMain(rs.getBoolean("is_main"));
        image.setAdId(rs.getInt("ad_id"));
    }
}
