package ru.innopolis.project.entity;

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
    private int userId;
    private int categoryId;
    private BigDecimal price;
    private BigDecimal priceMin;
    private Date date;
    private int dayCount;
    private boolean confirm;
    private boolean isActual;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int category_id) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(BigDecimal price_min) {
        this.priceMin = priceMin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean isActual) {
        this.isActual = isActual;
    }


    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                ", price=" + price +
                ", priceMin=" + priceMin +
                ", date=" + date +
                ", dayCount=" + dayCount +
                ", confirm=" + confirm +
                ", isActual=" + isActual +
                '}';
    }
}
