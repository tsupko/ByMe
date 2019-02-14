package ru.inno.project.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * POJO класс для таблицы ad (advertisement)
 *
 * @author Kuzina Anastasia
 */
public class Ad {

    private int id;
    private String title;
    private String text;
    private int user_id;
    private int category_id;
    private BigDecimal price;
    private BigDecimal price_min;
    private Date date;
    private int day_count;
    private boolean confirm;
    private boolean is_actual;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice_min() {
        return price_min;
    }

    public void setPrice_min(BigDecimal price_min) {
        this.price_min = price_min;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDay_count() {
        return day_count;
    }

    public void setDay_count(int day_count) {
        this.day_count = day_count;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public boolean isIs_actual() {
        return is_actual;
    }

    public void setIs_actual(boolean is_actual) {
        this.is_actual = is_actual;
    }


    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", user_id=" + user_id +
                ", category_id=" + category_id +
                ", price=" + price +
                ", price_min=" + price_min +
                ", date=" + date +
                ", day_count=" + day_count +
                ", confirm=" + confirm +
                ", is_actual=" + is_actual +
                '}';
    }
}
