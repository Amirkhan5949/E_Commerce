package com.example.e_commerce_admin.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.e_commerce_admin.R;

public class SIGNINFragment extends Fragment {

    EditText username,password;
    Button signin,fblogin;
    TextView forgotpass;
    View view;



    public SIGNINFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_blank, container, false);
        username=view.findViewById(R.id.u_name);
        password=view.findViewById(R.id.u_password);
        signin=view.findViewById(R.id.signin);
        fblogin=view.findViewById(R.id.fb);
        forgotpass=view.findViewById(R.id.forgotpassword);
        return view;
    }
}