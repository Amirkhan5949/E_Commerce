package com.example.e_commerce_admin.ui.fragment;






import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.ui.activity.MainActivity;
import com.example.e_commerce_admin.utils.Loader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
  * create an instance of this fragment.
 */
public class SIGNUPFragment extends Fragment {

    View view;
    EditText username,password,conpaass,email;
    Button signup;
    TextView signin;
    private Loader loader;


    public SIGNUPFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_s_i_g_n_u_p, container, false);


        username=view.findViewById(R.id.u_name);
        password=view.findViewById(R.id.u_password);
        conpaass=view.findViewById(R.id.u_confirmpassword);
        email=view.findViewById(R.id.u_email);
        signup=view.findViewById(R.id.signup);
        signin=view.findViewById(R.id.signin);
        loader = new Loader(getContext());

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).goToSignUp();
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(username.getText().toString().trim())){
                    Toast.makeText(getContext(), "Please enter user name", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(password.getText().toString().trim())){
                    Toast.makeText(getContext(), "Pleas Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if (password.getText().toString().length()<6){
                    Toast.makeText(getContext(), "Pleas Enter 6 or more than digit password", Toast.LENGTH_SHORT).show();
                }
                else if (!(password.getText().toString().trim()).equals(conpaass.getText().toString().trim())){
                    Toast.makeText(getContext(), "password not match", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(email.getText().toString().trim())){
                    Toast.makeText(getContext(), "Pleas Enter Email Address", Toast.LENGTH_SHORT).show();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()){
                    Toast.makeText(getContext(), "Pleas Enter valid Email Address", Toast.LENGTH_SHORT).show();
                }
                else {
                    registration();

                }
            }
        });
        return view;
    }

    private void registration() {
        loader.show();
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString().trim(),password.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {

                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("Username",username.getText().toString());
                        map.put("password",password.getText().toString());
                        map.put("confirmpassword",conpaass.getText().toString());
                        map.put("email",email.getText().toString());


                        FirebaseDatabase.getInstance().getReference()
                                .child("Admin")
                                .child(FirebaseAuth.getInstance().getUid())
                                .setValue(map)
                                .addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {

                                        Toast.makeText(getContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                                       loader.dismiss();


                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Failed :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.i("dfsfsv", "onFailure: "+e.getMessage());
                                Log.i("dfsfsv", "onFailure: "+toString());
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loader.dismiss();
                Toast.makeText(getContext(), "Failed :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("dfsfsv", "onFailure: "+e.getMessage());
                Log.i("dfsfsv", "onFailure: "+toString());
            }
        });
    }



}