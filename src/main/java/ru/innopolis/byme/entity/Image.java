package ru.innopolis.byme.entity;

/**
 * POJO класс для таблицы image
 *
 * @author Kuzina Anastasia
 */
public class Image {

    private int id;
    private String img;
    private boolean isMain;
    private int adId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean is_main) {
        this.isMain = isMain;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

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
