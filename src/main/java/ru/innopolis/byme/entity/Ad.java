package ru.innopolis.byme.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * POJO класс для таблицы ad (advertisement)
 *
 * @author Kuzina Anastasia
 */

@Data
public class Ad {

    private int id;
    private String title;
    private String text;
    private int userId;
    private int categoryId;
    private BigDecimal price;
    private BigDecimal priceMin;
    private Date date;
    private int dayCount;
    private boolean confirm;
    private boolean isActual;

    private Image image = new Image();
}
