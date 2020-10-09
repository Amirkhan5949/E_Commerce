package com.example.e_commerce_admin.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Color;
import com.example.e_commerce_admin.model.Images;
import com.example.e_commerce_admin.model.Product;
import com.example.e_commerce_admin.model.Size;
import com.example.e_commerce_admin.ui.adapter.Color_Adapter;
import com.example.e_commerce_admin.ui.adapter.MainSliderAdapter;
import com.example.e_commerce_admin.ui.adapter.ProductReview_Adapter;
import com.example.e_commerce_admin.ui.adapter.Size_Adapter;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ss.com.bannerslider.Slider;

public class ProductDetailActivity extends AppCompatActivity {
    Slider banner_slider;
    RecyclerView rv_color, rv_size;
    RecyclerView rv_review;
    private TextView tv_p_name, tv_details, tv_rs, tv_add;
    private List<Color> selectedColor = new ArrayList<>();
    private List<Images> imagesList = new ArrayList<>();
    private Size_Adapter size_adapter;
    private List<Size> selectedSize = new ArrayList<>();

    private Color_Adapter colorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        banner_slider = findViewById(R.id.banner_slider);

        rv_color = findViewById(R.id.rv_color);
        rv_size = findViewById(R.id.rv_size);
        tv_add = findViewById(R.id.tv_add);
        tv_p_name = findViewById(R.id.tv_p_name);
        tv_details = findViewById(R.id.tv_details);
        tv_rs = findViewById(R.id.tv_rs);


        rv_size.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        size_adapter = new Size_Adapter(getSize(), selectedSize);
        rv_size.setAdapter(size_adapter);

        rv_color.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        colorAdapter = new Color_Adapter(selectedColor);
        rv_color.setAdapter(colorAdapter);

        String id = getIntent().getStringExtra("id");

        FirebaseDatabase.getInstance().getReference()
                .child(FirebaseConstants.Product.key)
                .child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Product product = snapshot.getValue(Product.class);


                Map<String, Size> size = product.getSize();
                if (size != null && size.size() > 0) {
                    List<Size> sizes = new ArrayList<>();
                    for (Map.Entry<String, Size> entry : size.entrySet()) {
                        sizes.add(entry.getValue());
                    }
                    selectedSize.addAll(sizes);
                    size_adapter.notifyDataSetChanged();

                }

                Map<String, Images> imagesMap = product.getImage();
                Log.i("ettfbgfff", "onCreate: " + product.getImage().toString());
                if (imagesMap != null && imagesMap.size() > 0) {
                    List<Images> images = new ArrayList<>();
                    for (Map.Entry<String, Images> entry : imagesMap.entrySet()) {
                        images.add(entry.getValue());
                    }

                    Log.i("ettfbgf", "onCreate: " + images.toString());
                    imagesList.addAll(images);
                    Log.i("sfsfsf", "onDataChange: " + imagesList.toString());
                    Log.i("dsdsfs", "onCreate: " + images.toString());

                    banner_slider.setAdapter(new MainSliderAdapter(images));
                    banner_slider.setInterval(4000);
                }


                Map<String, Color> selectedColors = product.getSelectedColor();
                if (selectedColors != null && selectedColors.size() > 0) {
                    List<Color> colors = new ArrayList<>();
                    for (Map.Entry<String, Color> entry : selectedColors.entrySet()) {
                        colors.add(entry.getValue());
                    }
                    selectedColor.addAll(colors);
                    colorAdapter.notifyDataSetChanged();
                }

                tv_p_name.setText(product.getName());
                tv_details.setText(product.getDetails());
                tv_rs.setText(product.getSelling_price());

                Log.i("defdgdgrt", "onDataChange: " + snapshot.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        rv_review = findViewById(R.id.rv_review);
        rv_review.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_review.setAdapter(new ProductReview_Adapter());
    }

    private List<Size> getSize() {
        List<Size> list = new ArrayList<>();
        list.add(new Size("Small"));
        list.add(new Size("Large"));
        list.add(new Size("Medium"));
        list.add(new Size("Extra Large"));
        list.add(new Size("Double Extra Large"));
        return list;
    }


    List<Color> getcolor() {
        List<Color> list = new ArrayList<>();
        list.add(new Color("#000000"));
        list.add(new Color("#FF113F"));
        list.add(new Color("#FFFFFF"));
        list.add(new Color("#0019FE"));
        list.add(new Color("#00DC1D"));
        list.add(new Color("#FFC107"));
        list.add(new Color("#000000"));
        return list;
    }
}