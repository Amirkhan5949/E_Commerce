package com.example.e_commerce_admin.model;

public class Banner {

    String banner_id;
    String image;
    String image_format;

    public Banner() {
    }

    public String getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(String banner_id) {
        this.banner_id = banner_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_format() {
        return image_format;
    }

    public void setImage_format(String image_format) {
        this.image_format = image_format;
    }

    public Banner(String banner_id, String image, String image_format) {
        this.banner_id = banner_id;
        this.image = image;
        this.image_format = image_format;
    }

}
