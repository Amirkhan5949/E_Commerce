package com.example.e_commerce_admin.utils;

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

}
