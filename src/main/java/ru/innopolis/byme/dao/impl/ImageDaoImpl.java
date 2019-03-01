package ru.innopolis.byme.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import ru.innopolis.byme.dao.api.ImageDao;
import ru.innopolis.byme.entity.Image;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ImageDaoImpl implements ImageDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageDaoImpl.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ImageDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * sql-скрипт для создания записи в соответствующей таблице
     */
    private static final String INSERT_IMAGE = "insert into image" +
            " (img, is_main, ad_id)\n" +
            " values (?,?,?) returning id";

    /**
     * создание записи в БД с полями переданного экземпляра
     * в соответствующей таблице
     *
     * @param image объект, для которого будет создана запись в БД
     */

    @Override
    public void create(Image image) {

        if (image == null) {
            LOGGER.error("Попытка создавать запись в БД для image == null ");
            return;
        }
        LOGGER.debug("Создание фото объявления {}", image);
        this.jdbcTemplate.execute(INSERT_IMAGE, (PreparedStatementCallback<Image>) stmt -> {
            stmt.setString(1, image.getImg());
            stmt.setBoolean(2, image.isMain());
            stmt.setInt(3, image.getAdId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    image.setId(rs.getInt("id"));
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при возвращении id после создания фото объявления:", e);
            }
            LOGGER.info("Фото бъявления с id={} создано успешно", image.getId());
            return image;
        });
    }

    /**
     * sql-скрипт для проверки наличия фото с укзанным ad_id
     */
    private static final String SELECT_BY_AD_ID = "SELECT * FROM image WHERE ad_id = ?";

    /**
     * проверка наличия фото у объявления с указанным id
     *
     * @param adId id объявления к которому относится фото
     */
    @Override
    public boolean exists(int adId) {

        Boolean exists = this.jdbcTemplate.execute(SELECT_BY_AD_ID, (PreparedStatementCallback<Boolean>) stmt -> {
            stmt.setInt(1, adId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при проверке наличия image по ad_id={}: {}", adId, e);
            }
            return false;
        });
        return exists;
    }

    @Override
    public List<Image> getAll() {

        String sql = "select * from image";
        List<Image> images = new ArrayList<>();

        this.jdbcTemplate.execute(sql, (PreparedStatementCallback<List<Image>>) stmt -> {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Image image = new Image();
                    image.setMain(true);
                    image.setAdId(rs.getInt("ad_id"));
                    image.setImg(rs.getString("img"));
                    image.setId(rs.getInt("id"));
                    LOGGER.info(image.toString());
                    images.add(image);
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при получении всех image из таблицы image ", e);
            }
            LOGGER.info(images.toString());
            return (images);
        });
        System.err.println(images);
        return (images);
    }

    @Override
    public Image getImageByAd(int adId) {
        Image image = new Image();

        this.jdbcTemplate.execute(SELECT_BY_AD_ID, (PreparedStatementCallback<Image>) stmt -> {
            stmt.setInt(1, adId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    image.setId(rs.getInt("id"));
                    image.setImg(rs.getString("img"));
                    image.setAdId(rs.getInt("ad_id"));
                    image.setMain(rs.getBoolean("is_main"));
                    return image;
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при проверке наличия image по ad_id={}: {}", adId, e);
            }
            return image;
        });
        LOGGER.info("Новое фото объявления: {}", image);
        return image;
    }
}
