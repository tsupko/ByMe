package ru.innopolis.byme.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * POJO класс для таблицы image
 *
 * @author Kuzina Anastasia
 */

@Getter
@Setter
public class Image {

    private int id;
    private String img;
    private boolean isMain;
    private int adId;

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", isMain=" + isMain +
                ", adId=" + adId +
                '}';
    }
}
