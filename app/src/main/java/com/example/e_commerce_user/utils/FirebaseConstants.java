package com.example.e_commerce_user.utils;

public class FirebaseConstants {
    public static class SuperCategory {
        public static final String key = "SuperCategory";
        public static final String super_category_id = "id";
        public static final String name = "name";
        public static final String image = "image";
        public static final String image_format = "image_format";
    }

    public static class Category{
        public static final String key = "Category";
        public static final String super_category = "super_category";
        public static final String super_id = "super_category_id";
        public static final String name = "name";
        public static final String image = "image";
        public static final String image_format = "image_format";
    }

    public static class Brand{
        public static final String key = "Brand";
        public static final String brand_id = "brand_id";
        public static final String name = "name";
        public static final String image = "image";
        public static final String image_format = "image_format";

    }
    public static class Product{
        public static final String key = "Product";
        public static final String product_id = "product_id";
        public static final String super_category = "super_category";
        public static final String super_category_id = "super_category_id";
        public static final String category = "category";
        public static final String category_id = "category_id";
        public static final String brand = "brand";
        public static final String brand_id = "brand_id";
        public static final String details = "details";
        public static final String items_rs = "selling_price";
        public static final String items_cross_rs = "mrp_price";
        public static final String name = "name";
        public static final String Image = "Image";
        public static final String img = "img";
        public static final String image_format = "image_format";
        public static final String Size = "Size";
        public static final String Color = "Color";
        public static final String SelectedColor = "SelectedColor";
        public static final String SelectedProductList = "SelectedProductLists";

    }

    public static class ProductRecommendedList{
        public static final String key = "ProductRecommendedList";
        public static final String name = "name";
        public static final String id = "id";
        public static final String Product = "Product";
    }


    public static class Banner_Slider{
        public static final String key = "Banner";
        public static final String banner_id = "banner_id";
        public static final String image = "image";
        public static final String image_format = "image_format";
    }

    public static class WishList{
        public static final String key="WishList";
    }

    public static class Cart{
        public static final String key="Cart";
        public static final String Product="Product";
        public static final String Color ="Color";
        public static final String size ="size";
        public static final String quantity = "quantity";
        public static final String discount ="discount";
        public static final String finalPrice ="finalPrice";


    }

    public static class Address{
        public static final String key="Address";
        public static final String pincode="pincode";
        public static final String address="address";
        public static final String state="state";
        public static final String city="city";
        public static final String landmark="landmark";
        public static final String name="name";
        public static final String mob_no="mob_no";
        public static final String address_type="address_type";
        public static final String default_address_index="default_address_index";
    }

    public static class Profile{
        public static final String key="Profile";
        public static final String name="name";
        public static final String email="email";
        public static final String mobile_no="mobile_no";
        public static final String mobile="mob_no";
        public static final String address = "address";
        public static final String dob="dob";

    }

    public static class Order{
        public static final String key="Order";
        public static final String mrp_price="mrp_price";
        public static final String selling_price="selling_price";
        public static final String ordered_mrp_price="ordered_mrp_price";
        public static final String ordered_selling_price="ordered_selling_price";
        public static final String quantity="quantity";
        public static final String payment_type="payment_type";
        public static final String order_status="order_status";
        public static final String Product="Product";
        public static final String Address="address";
        public static final String user_id="user_id";
        public static final String time="time";




    }

    public static class User{
        public static final String key="Users";
        public static final String Username="Username";
        public static final String confirmpassword="confirmpassword";
        public static final String dob="dob";
        public static final String email="email";
        public static final String image="image";
        public static final String image_format="image_format";
        public static final String mobile_no="mobile_no";
        public static final String name="name";
        public static final String password="password";


    }

}
