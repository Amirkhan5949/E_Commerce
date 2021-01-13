package com.example.e_commerce_user.model;

public class Order{
    Product Product;
    String ordered_mrp_price;
    String ordered_selling_price;
    String order_status;
    boolean review_status;

    public boolean getReview_status() {
        return review_status;
    }

    public void setReview_status(boolean review_status) {
        this.review_status = review_status;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }



    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    String payment_type;
    String color;
    String size;
    int quantity;

    @Override
    public String toString() {
        return "Order{" +
                "Product=" + Product +
                ", ordered_mrp_price='" + ordered_mrp_price + '\'' +
                ", ordered_selling_price='" + ordered_selling_price + '\'' +
                ", payment_type='" + payment_type + '\'' +
                ", time=" + time +
                ", quantity=" + quantity +
                '}';
    }

    public Order(com.example.e_commerce_user.model.Product product) {
        Product = product;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    long time;


    public Order(com.example.e_commerce_user.model.Product product, String ordered_mrp_price, String ordered_selling_price, String payment_type, int quantity) {
        Product = product;
        this.ordered_mrp_price = ordered_mrp_price;
        this.ordered_selling_price = ordered_selling_price;
        this.payment_type = payment_type;
        this.quantity = quantity;
    }

    public com.example.e_commerce_user.model.Product getProduct() {
        return Product;
    }

    public void setProduct(com.example.e_commerce_user.model.Product product) {
        Product = product;
    }

    public String getOrdered_mrp_price() {
        return ordered_mrp_price;
    }

    public void setOrdered_mrp_price(String ordered_mrp_price) {
        this.ordered_mrp_price = ordered_mrp_price;
    }

    public String getOrdered_selling_price() {
        return ordered_selling_price;
    }

    public void setOrdered_selling_price(String ordered_selling_price) {
        this.ordered_selling_price = ordered_selling_price;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order() {
    }


}
