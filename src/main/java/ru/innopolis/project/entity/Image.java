package ru.innopolis.project.entity;

/**
 * POJO класс для таблицы image
 *
 * @author Kuzina Anastasia
 */
public class Image {

    private int id;
    private String img;
    private boolean is_main;
    private int ad_id;

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

    public boolean isIs_main() {
        return is_main;
    }

    public void setIs_main(boolean is_main) {
        this.is_main = is_main;
    }

    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", is_main=" + is_main +
                ", ad_id=" + ad_id +
                '}';
    }
}
