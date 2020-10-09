package com.example.e_commerce_admin.model;

public class SuperCategory {
    String id;
    String image;
    String name;

    public SuperCategory() {
    }

    @Override
    public String toString() {
        return "SuperCategory{" +
                "id='" + id + '\'' +
                ", image='" + image + '\'' +
                ", image_format='" + image_format + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    String image_format;

    public SuperCategory(String id, String image, String image_format, String name) {
        this.id = id;
        this.image = image;
        this.image_format = image_format;
        this.name = name;
    }
}
