package com.example.e_commerce_admin.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.ui.activity.HomeActivity2;
import com.example.e_commerce_admin.utils.Loader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SIGNINFragment extends Fragment {

    EditText username,password;
    Button signin,fblogin;
    TextView forgotpass;
    View view;
    Loader loader;



    public SIGNINFragment()  {
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

          loader=new Loader(getContext());


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(username.getText().toString())){
                    Toast.makeText(getContext(), "Pleas Enter Email Address", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(username.getText().toString()).matches()){
                    Toast.makeText(getContext(), "Pleas Enter valid Email Address", Toast.LENGTH_SHORT).show();
                }
                else  if(TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(getContext(), "Pleas Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().toString().length()<6){
                    Toast.makeText(getContext(), "Pleas Enter 6 or more than digit password", Toast.LENGTH_SHORT).show();
                }
                else {
                    login();
                }
            }
        });
          return view;
    }

    private void login() {
        loader.show();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(username.getText().toString(),password.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getActivity(), "Login Successful.... ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(),HomeActivity2.class));
                        getActivity().finish();
                        loader.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        loader.dismiss();
                        Toast.makeText(getActivity(), "Failed :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}