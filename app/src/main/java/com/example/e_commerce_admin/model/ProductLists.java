package com.example.e_commerce_admin.model;

public class ProductLists {
    String name;
    String id;

    public ProductLists() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductRecommended{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
