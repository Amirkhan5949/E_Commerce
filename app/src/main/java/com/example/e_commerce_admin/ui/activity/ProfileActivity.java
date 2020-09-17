package com.example.e_commerce_admin.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.e_commerce_admin.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.io.File;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    ImageView iv_edit_name,iv_edit_add,iv_edit_profile;
//    ImagePicker imagePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        iv_edit_name=findViewById(R.id.iv_edit_name);
        iv_edit_add=findViewById(R.id.iv_edit_add);
        iv_edit_profile=findViewById(R.id.iv_edit_profile);

        iv_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                if (!hasPermissions( getContext(), PERMISSIONS)) {
//                    ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_ALL);
//                }
//                else {
//                    imagePicker.choosePicture(true /*show camera intents*/);
//
//                }


            }
        });


        final String[] PERMISSIONS = {
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
        };

//        imagePicker = new ImagePicker(this, /* activity non null*/
//                this, /* fragment nullable*/
//                new OnImagePickedListener() {
//                    @Override
//                    public void onImagePicked(Uri imageUri) {
//                        UCrop.of(imageUri, getTempUri())
//                                .withAspectRatio(1, 1)
//                                .start ((AppCompatActivity) getActivity());
//
//
//                    }
//                }
//
//        );


        iv_edit_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(iv_edit_add.getContext(),AddressActivity.class);
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

                Button button=viewholder.findViewById(R.id.btn_save);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });



            }
        });


     }


    private  Uri getTempUri(){

        String dri= Environment.getExternalStorageDirectory()+ File.separator+"Temp";

        File drifile= new File(dri);
        drifile.mkdir();

        String file=dri+File.separator+"Temp.png";
        File tempfile=new File(file);
        try {
            tempfile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Uri.fromFile(tempfile);
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


//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissionsList[], int[] grantResults) {
//
//        super.onRequestPermissionsResult(requestCode, permissionsList, grantResults);
//        imagePicker.handlePermission(requestCode, grantResults);
//        switch (requestCode) {
//            case PERMISSION_ALL:{
//                if (grantResults.length > 0) {
//                    boolean flag=true;
//                    for (int i=0;i<permissionsList.length;i++) {
//                        if(grantResults[i] == PackageManager.PERMISSION_DENIED){
//
//                            flag=false;
//                        }
//                    }
//
//                    if (flag=true){
//                        imagePicker.choosePicture(true /*show camera intents*/);
//
//                    }
//                    // Show permissionsDenied
//                }
//                return;
//            }
//        }
//    }

}
