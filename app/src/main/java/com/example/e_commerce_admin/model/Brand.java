package com.example.e_commerce_admin.model;

public class Brand {
    String brand_id;
    String image;
    String image_format;
    String name;

    public Brand(String brand_id, String image, String image_format, String name) {
        this.brand_id = brand_id;
        this.image = image;
        this.image_format = image_format;
        this.name = name;
    }

    public Brand() {
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brand_id='" + brand_id + '\'' +
                ", image='" + image + '\'' +
                ", image_format='" + image_format + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
