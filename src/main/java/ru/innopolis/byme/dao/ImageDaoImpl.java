package ru.innopolis.byme.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import ru.innopolis.byme.entity.Image;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                LOGGER.error("Исключение при возвращении id после создания фото объявления: {}", e);
            }
            LOGGER.info("Фото бъявления с id={} создано успешно", image.getId());
            return image;
        });
    }
}
