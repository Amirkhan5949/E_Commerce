package com.example.e_commerce_admin.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.example.e_commerce_admin.utils.Loader;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myhexaville.smartimagepicker.ImagePicker;
import com.myhexaville.smartimagepicker.OnImagePickedListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    ImageView iv_edit_name, iv_edit_add, iv_edit_profile;
    ImagePicker imagePicker;
    TextView tv_name,tv_email,tv_mob_no,tv_dob;
    CircleImageView imageView;
    private final int PERMISSION_ALL = 1234;
    Loader loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        iv_edit_name = findViewById(R.id.iv_edit_name);
        iv_edit_add = findViewById(R.id.iv_edit_add);
        tv_name = findViewById(R.id.tv_name);
        tv_email = findViewById(R.id.tv_email);
        tv_dob = findViewById(R.id.tv_dob);
        tv_mob_no = findViewById(R.id.tv_mob_no);
        iv_edit_profile = findViewById(R.id.iv_edit_profile);
        imageView=findViewById(R.id.iv_profile);
        loader=new Loader(this);


        FirebaseDatabase.getInstance().getReference().child("Admin")
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        tv_email.setText(snapshot.child(FirebaseConstants.Profile.email).getValue(String.class));
                        tv_dob.setText(snapshot.child(FirebaseConstants.Profile.dob).getValue(String.class));
                         tv_mob_no.setText(snapshot.child(FirebaseConstants.Profile.mobile_no).getValue(String.class));
                        tv_name.setText(snapshot.child(FirebaseConstants.Profile.name).getValue(String.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        final String[] PERMISSIONS = {
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
        };





        iv_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!hasPermissions(ProfileActivity.this, PERMISSIONS)) {
                    ActivityCompat.requestPermissions(ProfileActivity.this, PERMISSIONS, PERMISSION_ALL);
                } else {
                    imagePicker.choosePicture(true /*show camera intents*/);
                }


            }
        });

        imagePicker = new ImagePicker(this, /* activity non null*/
                null, /* fragment nullable*/
                new OnImagePickedListener() {
                    @Override
                    public void onImagePicked(Uri imageUri) {
                        UCrop.of(imageUri, getTempUri())
                                .withAspectRatio(1, 1)
                                .start(ProfileActivity.this);
                    }
                }

        );


        iv_edit_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(iv_edit_add.getContext(), AddressActivity.class);
                startActivity(intent);
            }
        });
        iv_edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialog = DialogPlus.newDialog(iv_edit_name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.edit))
                        .setCancelable(false)
                        .setGravity(Gravity.TOP)
                        .create();
                dialog.show();

                View viewholder = dialog.getHolderView();

                ImageView iv_back=viewholder.findViewById(R.id.iv_back);
                final EditText et_name=viewholder.findViewById(R.id.et_name);
                 final EditText et_mobno=viewholder.findViewById(R.id.et_mobno);
                final EditText et_dob=viewholder.findViewById(R.id.et_dob);

                et_name.setText(tv_name.getText());
                 et_dob.setText(tv_dob.getText());
                et_mobno.setText(tv_mob_no.getText());

                iv_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                Button button = viewholder.findViewById(R.id.btn_save);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String,Object> map = new HashMap<>();
                         map.put(FirebaseConstants.Profile.name,et_name.getText().toString());
                        map.put(FirebaseConstants.Profile.mobile_no,et_mobno.getText().toString());
                        map.put(FirebaseConstants.Profile.dob,et_dob.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Admin")
                                .child(FirebaseAuth.getInstance().getUid())
                                .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.i("dfsfsf", "onComplete: "+task.isSuccessful());
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("dfsfsf", "onFailure: "+e.getMessage().toString());
                            }
                        });


                        dialog.dismiss();
                    }
                });


            }
        });


    }

    private Uri getTempUri(){
        String dri = Environment.getExternalStorageDirectory()+File.separator+"Temp";
        File dirFile = new File(dri);
        dirFile.mkdir();
        String file = dri+File.separator+"temp.png";
        File tempFile = new File(file);
        try {
            tempFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Uri.fromFile(tempFile);
    }




    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissionsList[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissionsList, grantResults);
        imagePicker.handlePermission(requestCode, grantResults);
        switch (requestCode) {
            case PERMISSION_ALL: {
                if (grantResults.length > 0) {
                    boolean flag = true;
                    for (int i = 0; i < permissionsList.length; i++) {
                        if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                            flag = false;
                        }
                    }

                    if (flag = true) {
                        imagePicker.choosePicture(true /*show camera intents*/);

                    }
                    // Show permissionsDenied
                }
                return;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.handleActivityResult(resultCode, requestCode, data);

        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            imageView.setImageURI(null);
            imageView.setImageURI(resultUri);
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
            Log.i("dsjknjsdkn", "onActivityResult: "+cropError.getMessage());
        }
        Log.i("nhnnhnghghn", "onActivityResult: done ");
    }


 }
