package com.example.e_commerce_user.model;

public class Cart {
    Product Product;
    int discount;
    int quantity;
    Size size;
    Color Color;

    @Override
    public String toString() {
        return "Cart{" +
                "Product=" + Product +
                ", discount=" + discount +
                ", quantity=" + quantity +
                ", size=" + size +
                ", Color=" + Color +
                '}';
    }

    public Cart() {
    }

    public com.example.e_commerce_user.model.Product getProduct() {
        return Product;
    }

    public void setProduct(com.example.e_commerce_user.model.Product product) {
        Product = product;
    }

    public String getDiscount() {
        return String.valueOf(discount);
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Color getColor() {
        return Color;
    }

    public void setColor(Color Color) {
        this.Color = Color;
    }

}
