package ru.innopolis.byme.entity;

import lombok.Data;

@Data
public class Unity {
    private Ad ad;
    private Image image;

    public Unity(Ad ad, Image image) {
        this.ad = ad;
        this.image = image;
    }
}
