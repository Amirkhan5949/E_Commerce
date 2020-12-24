package com.example.e_commerce_admin.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.SuperCategory;
import com.example.e_commerce_admin.ui.adapter.Super_Cat_viewAdapter;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    private View view;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private String id,type;
    private int position;
    private TabLayout tabLayout;



    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(String id, String type, int position){
        CategoryFragment f = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        args.putInt("position", position);
        args.putString("type", type);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_category, container, false);
//        ((HomeActivity)getActivity()).setCheckedNavigationItem(2);

       init();

        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
            type = args.getString("type");
            position= args.getInt("position");
            Log.i("esdwdwd", "frag: "+position);

         }


        Log.i("dgrrted", "onCreateView: "+position);
        if (type.equals("Fromadapter")){
            toolbar.setVisibility(View.GONE);

        }else {
            toolbar.setVisibility(View.VISIBLE);
        }

        FirebaseDatabase.getInstance().getReference()
                .child(FirebaseConstants.SuperCategory.key)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<SuperCategory> superCategories=new ArrayList<>();
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                            SuperCategory value = dataSnapshot.getValue(SuperCategory.class);
                            superCategories.add(value);
                        }

                        viewPager.setAdapter(new Super_Cat_viewAdapter(getChildFragmentManager(),superCategories));
                        tabLayout.setupWithViewPager(viewPager);
                        viewPager.setCurrentItem(position);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        return view;
    }

    private void init() {
        viewPager=view.findViewById(R.id.viewPager);
        tabLayout=view.findViewById(R.id.tabLayout);
        toolbar=view.findViewById(R.id.toolbar);

    }


}