package ru.innopolis.byme.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import ru.innopolis.byme.entity.Category;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDaoImpl.class);
    private JdbcTemplate jdbcTemplate;

    public CategoryDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * соответствующие названия полей в таблице category
     */
    private static final String CATEGORY_ID = "id";
    private static final String CATEGORY_NAME = "name";
    private static final String CATEGORY_PARENT_ID = "parent_id";


    /**
     * sql-скрипт для выбора всех категорий из таблицы category
     */
    private static final String SELECT_ALL_CATEGORIES = "Select * from category";

    @Override
    public Collection<Category> getAll() {
        LOGGER.info("getAllCategory");
        Collection<Category> categories = new ArrayList<>();
        this.jdbcTemplate.execute(SELECT_ALL_CATEGORIES, (PreparedStatementCallback<Collection<Category>>) stmt -> {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category();
                    assignResultSetToCategoryFields(rs, category);
                    categories.add(category);
                }
            } catch (SQLException e) {
                LOGGER.error("Исключение при получении всех категорий из таблицы category ", e);
            }
            LOGGER.info(categories.toString());
            return (categories);
        });
        return (categories);
    }

    private void assignResultSetToCategoryFields(ResultSet rs, Category category) throws SQLException {
        category.setId(rs.getInt(CATEGORY_ID));
        category.setName(rs.getString(CATEGORY_NAME));
        category.setParentId(rs.getInt(CATEGORY_PARENT_ID));
    }
}
