package com.example.e_commerce_user.model;

public class WishList {
    String title;
    String id;
    String product_id;
    String img;

    @Override
    public String toString() {
        return "WishList{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public WishList() {
    }

    public WishList(String title, String id, String product_id) {
        this.title = title;
        this.id = id;
        this.product_id = product_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

}
