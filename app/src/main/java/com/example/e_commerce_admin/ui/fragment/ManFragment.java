package com.example.e_commerce_admin.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.GridSpacingItemDecoration;
import com.example.e_commerce_admin.model.SuperCategory;
import com.example.e_commerce_admin.ui.activity.HomeActivity;
import com.example.e_commerce_admin.ui.adapter.Cat_Adapter;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.example.e_commerce_admin.utils.util;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManFragment extends Fragment {

    private View view;
    private RecyclerView catrecycler;
    private Cat_Adapter adapter;
    private ProgressBar progress;
    final DatabaseReference base = FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Category.key);
    private String id;


    public static ManFragment newInstance(String id){
        ManFragment f = new ManFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        f.setArguments(args);
        return f;
    }


    public ManFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_man, container, false);

        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
            Log.i("fsfsfs", "onCreateView: " + id);
        }

       init();



        catrecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
        catrecycler.addItemDecoration(new GridSpacingItemDecoration(3, util.dpToPx(getContext(),16),true));

        FirebaseRecyclerOptions<SuperCategory> options =
                new FirebaseRecyclerOptions.Builder<SuperCategory>()
                        .setQuery(base.orderByChild(FirebaseConstants.Category.super_id).equalTo(id), SuperCategory.class)
                        .build();
           adapter=new Cat_Adapter(options, progress, new Cat_Adapter.ClickCallBack() {
               @Override
               public void click(int i, String id) {
//                   ((HomeActivity)getActivity()).replace(ProductListFragment.newInstance(id),"ProductListFragment");
               }


           });
        catrecycler.setAdapter(adapter);


        base.orderByChild(FirebaseConstants.Category.super_id).equalTo(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.i("sdkfjhdkj", "onDataChange: "+snapshot.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.i("sdkfjhdkj", "onCancelled: "+error.getMessage());
                    }
                });
        return view;
    }

    private void init() {
        catrecycler=view.findViewById(R.id.cat_recycler);
        progress=view.findViewById(R.id.progress);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}