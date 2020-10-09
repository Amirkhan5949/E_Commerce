package com.example.e_commerce_admin.model;

public class Images {
    String img;
    String image_format;

    public Images() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImage_format() {
        return image_format;
    }

    public void setImage_format(String image_format) {
        this.image_format = image_format;
    }

    @Override
    public String toString() {
        return "Images{" +
                "Image='" + img + '\'' +
                ", image_format='" + image_format + '\'' +
                '}';
    }
}
