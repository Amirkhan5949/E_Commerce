package com.example.e_commerce_user.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.Color;
import com.example.e_commerce_user.model.Images;
import com.example.e_commerce_user.model.Product;
import com.example.e_commerce_user.model.Size;
import com.example.e_commerce_user.ui.adapter.Color_Adapter;
import com.example.e_commerce_user.ui.adapter.MainSliderAdapter;
import com.example.e_commerce_user.ui.adapter.ProductReview_Adapter;
import com.example.e_commerce_user.ui.adapter.Size_Adapter;
import com.example.e_commerce_user.utils.FirebaseConstants;
import com.example.e_commerce_user.utils.Loader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ss.com.bannerslider.Slider;

public class ProductDetailActivity extends AppCompatActivity {

    private Slider banner_slider;
    private RecyclerView rv_color, rv_size;
    private ImageView iv_back,icon_favourite,iv_cart;
    private RecyclerView rv_review;
    private TextView tv_p_name, tv_details, tv_rs, tv_add;
    private List<Color> selectedColor = new ArrayList<>();
    private List<Images> imagesList = new ArrayList<>();
    private Size_Adapter size_adapter;
    private List<Size> sizeList = new ArrayList<>();
    boolean heart=true;
    private Loader loader;

    private Color_Adapter colorAdapter;
    private TextView title;

     private Product product;
     private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        init();

        setUpToolbar();

        initViews();


        iv_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(ProductDetailActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });

        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_add.getText().equals("Add")){
                    addToCart();
                }
                else {
                    removeTOCart();
                }
            }
        });

        if (FirebaseAuth.getInstance().getUid()!=null){

            icon_favourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (heart){
                        removeToWishList();
                    }
                    else{
                        addToWishlist();
                    }
                }
            });

        }

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rv_size.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        size_adapter = new Size_Adapter(sizeList);
        rv_size.setAdapter(size_adapter);

        rv_color.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        colorAdapter = new Color_Adapter(selectedColor);
        rv_color.setAdapter(colorAdapter);



        FirebaseDatabase.getInstance().getReference()
                .child(FirebaseConstants.Product.key)
                .child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                product = snapshot.getValue(Product.class);




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

                    banner_slider.setAdapter(new MainSliderAdapter(images,""));
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


                Map<String, Size> sizeMap = product.getSize();
                if (sizeMap != null && sizeMap.size() > 0) {
                    List<Size> sizes = new ArrayList<>();
                    for (Map.Entry<String, Size> entry : sizeMap.entrySet()) {
                        sizes.add(entry.getValue());
                    }
                    sizeList.addAll(sizes);
                    size_adapter.notifyDataSetChanged();
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

    private void initViews() {
        if (FirebaseAuth.getInstance().getUid()!=null){
            FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.WishList.key)
                    .child(FirebaseAuth.getInstance().getUid())
                    .child(id)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                icon_favourite.setImageResource(R.drawable.filledheart);
                                heart = true;
                            }
                            else {
                                icon_favourite.setImageResource(R.drawable.favorite);
                                heart = false;
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

        }


        if (FirebaseAuth.getInstance().getUid()!=null){
            FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Cart.key)
                    .child(FirebaseAuth.getInstance().getUid())
                    .child(id)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                tv_add.setText("Remove");
                            }
                            else {
                                tv_add.setText("Add");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

        }
     }

    private void removeToWishList() {
        FirebaseDatabase.getInstance().getReference()
                .child(FirebaseConstants.WishList.key)
                .child(FirebaseAuth.getInstance().getUid())
                .child(id)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        heart = false;
                        icon_favourite.setImageResource(R.drawable.favorite);
                    }
                });
    }

    private void addToWishlist() {
        loader.show();
          Map<String,Object> map = new HashMap<>();
          map.put(id,product);

          FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.WishList.key)
                  .child(FirebaseAuth.getInstance().getUid())
                  .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
              @Override
              public void onSuccess(Void aVoid) {
                  loader.dismiss();
                  icon_favourite.setImageResource(R.drawable.filledheart);
                  heart = true;
              }
          }).addOnFailureListener(new OnFailureListener() {
              @Override
              public void onFailure(@NonNull Exception e) {
                  loader.dismiss();
              }
          });


    }

    private void setUpToolbar() {
        final Toolbar tool = (Toolbar)findViewById(R.id.toolbar);
        title = tool.findViewById(R.id.title);
        CollapsingToolbarLayout c = (CollapsingToolbarLayout)findViewById(R.id.collapsingToolbar);
        AppBarLayout appbar = (AppBarLayout)findViewById(R.id.appbar);
        tool.setTitle("");
        setSupportActionBar(tool);
        c.setTitleEnabled(false);

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            boolean isVisible = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isVisible = true;
                    title.setVisibility(View.VISIBLE);
                } else if(isVisible) {
                    isVisible = false;
                    title.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void init() {
        id = getIntent().getStringExtra("id");

        loader=new Loader(this);
        banner_slider = findViewById(R.id.banner_slider);

        rv_color = findViewById(R.id.rv_color);
        iv_cart = findViewById(R.id.iv_cart);
        icon_favourite = findViewById(R.id.icon_favourite);
        iv_back = findViewById(R.id.iv_back);
        rv_size = findViewById(R.id.rv_size);
        tv_add = findViewById(R.id.tv_add);
        tv_p_name = findViewById(R.id.tv_p_name);
        tv_details = findViewById(R.id.tv_details);
        tv_rs = findViewById(R.id.tv_rs);
        title = findViewById(R.id.title);

    }

    private void addToCart() {
        loader.show();

        if (colorAdapter.getSelectedPosition()==-1){
            Toast.makeText(this, "Select Color...", Toast.LENGTH_SHORT).show();
           loader.dismiss();
            return;
        }

        else if (size_adapter.getSelectedPosition()==-1){
            Toast.makeText(this, "Select Size...", Toast.LENGTH_SHORT).show();
           loader.dismiss();
            return;
        }


        Map<String,Object> map = new HashMap<>();
        map.put(FirebaseConstants.Cart.Product,product);
        map.put(FirebaseConstants.Cart.quantity,1);
        map.put(FirebaseConstants.Cart.discount,0);
         map.put(FirebaseConstants.Cart.Color,selectedColor.get(colorAdapter.getSelectedPosition()));
        map.put(FirebaseConstants.Cart.size,sizeList.get(size_adapter.getSelectedPosition()));


        FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Cart.key)
                .child(FirebaseAuth.getInstance().getUid())
                .child(id)
                .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                loader.dismiss();
                tv_add.setText("Remove");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loader.dismiss();
            }
        });


        Log.i("sgfyf", "addToCart: "+size_adapter.getSelectedPosition());
        Log.i("sgfyf", "addToCart: "+sizeList.get(size_adapter.getSelectedPosition()).getTitle());


    }
    private void removeTOCart(){
        loader.show();
        FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Cart.key)
                .child(FirebaseAuth.getInstance().getUid())
                .child(id)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                loader.dismiss();
                tv_add.setText("Add");
            }
        });

    }

  }