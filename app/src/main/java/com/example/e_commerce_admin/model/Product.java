package com.example.e_commerce_admin.model;

import java.util.Map;

public class Product {
    String super_category;
    String super_category_id;
    String category;
    String category_id;
    String brand;
    String brand_id;
    String id;
    String name;
    String details;
    String mrp_price;
    String selling_price;
    String img;


    Map<String,Images>  Image;

    Map<String,Size> Size;

    public Map<String, com.example.e_commerce_admin.model.Size> getSize() {
        return Size;
    }

    public void setSize(Map<String, com.example.e_commerce_admin.model.Size> size) {
        Size = size;
    }

    public Map<String, Images> getImage() {
        return Image;
    }

    public void setImage(Map<String, Images> image) {
        Image = image;
    }

    Map<String,Color> Color;

    public Map<String, com.example.e_commerce_admin.model.Color> getSelectedColor() {
        return SelectedColor;
    }

    public void setSelectedColor(Map<String, com.example.e_commerce_admin.model.Color> selectedColor) {
        SelectedColor = selectedColor;
    }

    Map<String,Color> SelectedColor;

    public Product() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }



    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMrp_price() {
        return mrp_price;
    }

    public void setMrp_price(String mrp_price) {
        this.mrp_price = mrp_price;
    }

    public String getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(String selling_price) {
        this.selling_price = selling_price;
    }

    public Map<String, Color> getColor() {
        return Color;
    }

    public void setColor(Map<String,Color> color) {
        Color = color;
    }

    public String getSuper_category() {
        return super_category;
    }

    public void setSuper_category(String super_category) {
        this.super_category = super_category;
    }

    public String getSuper_category_id() {
        return super_category_id;
    }

    public void setSuper_category_id(String super_category_id) {
        this.super_category_id = super_category_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
