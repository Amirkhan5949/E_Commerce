package com.example.e_commerce_user.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.UploadRequest;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.e_commerce_user.R;
import com.example.e_commerce_user.utils.FirebaseConstants;
import com.example.e_commerce_user.utils.ImagePickerHelper;
import com.example.e_commerce_user.utils.Loader;
import com.example.e_commerce_user.utils.PermissionHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myhexaville.smartimagepicker.ImagePicker;
import com.myhexaville.smartimagepicker.OnImagePickedListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.e_commerce_user.utils.PermissionHelper.setUpPermission;
import static pub.devrel.easypermissions.EasyPermissions.hasPermissions;

public class ProfileActivity extends AppCompatActivity {

    private final int PERMISSION_ALL = 1234;
    private Loader loader;
    private ProgressBar  progressBar;
    private ImageView iv_edit_name, iv_edit_add, iv_edit_profile;
    private ImagePicker imagePicker;
    private TextView tv_name, tv_email, tv_mob_no, tv_dob, tv_no, tv_u_name, tv_address;
    private CircleImageView imageView;
    private LinearLayout ll_mains;
    private UploadRequest imageRequest;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Profile.key);
    private int TOTAL_API_CALL = 3 , CURRENT_API_CALL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();

        FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.User.key)
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        showUi();
                        Picasso.get().load(snapshot.child("image").getValue(String.class)).into(imageView);
                        Log.i("avanichutiya", "onDataChange: "+snapshot.child("image"));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        showUi();
                    }
                });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setUpPermission(ProfileActivity.this))
                    imagePicker.choosePicture(true /*show camera intents*/);
            }
        });

        imagePicker = ImagePickerHelper.getInstance(this);


        FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.User.key)
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        showUi();
                        Log.i("avanichutiya", "onDataChange: "+snapshot.toString());
                        tv_email.setText(snapshot.child(FirebaseConstants.Profile.email).getValue(String.class));
                        if (snapshot.child(FirebaseConstants.Profile.dob).getValue(String.class)!=null){
                            tv_dob.setText(snapshot.child(FirebaseConstants.Profile.dob).getValue(String.class));
                        }else {
                            tv_dob.setText("DOB Not Set");
                        }
                        if (snapshot.child(FirebaseConstants.Profile.mobile_no).getValue(String.class)!=null){
                            tv_mob_no.setText(snapshot.child(FirebaseConstants.Profile.mobile_no).getValue(String.class));
                        }
                        else {
                            tv_mob_no.setText("Mobile No Not Set");
                        }
                        if (snapshot.child(FirebaseConstants.Profile.name).getValue(String.class)!=null){
                            tv_name.setText(snapshot.child(FirebaseConstants.Profile.name).getValue(String.class));

                        }
                        else {
                            tv_name.setText("User Name Not Set");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        showUi();
                    }
                });

        FirebaseDatabase.getInstance().getReference().child("Address")
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override

                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        showUi();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                            tv_no.setText(dataSnapshot.child(FirebaseConstants.Profile.mobile).getValue(String.class));
                            tv_u_name.setText(dataSnapshot.child(FirebaseConstants.Profile.name).getValue(String.class));
                            tv_address.setText(dataSnapshot.child(FirebaseConstants.Profile.address).getValue(String.class));
                            break;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        showUi();
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

                ImageView iv_back = viewholder.findViewById(R.id.iv_back);
                final EditText et_name = viewholder.findViewById(R.id.et_name);
                final EditText et_mobno = viewholder.findViewById(R.id.et_mobno);
                final EditText et_dob = viewholder.findViewById(R.id.et_dob);

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

                        Map<String, Object> map = new HashMap<>();
                        map.put(FirebaseConstants.Profile.name, et_name.getText().toString());
                        map.put(FirebaseConstants.Profile.mobile_no, et_mobno.getText().toString());
                        map.put(FirebaseConstants.Profile.dob, et_dob.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.User.key)
                                .child(FirebaseAuth.getInstance().getUid())
                                .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.i("dfsfsf", "onComplete: " + task.isSuccessful());
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("dfsfsf", "onFailure: " + e.getMessage());
                            }
                        });


                        dialog.dismiss();
                    }
                });


            }
        });


    }

    private void init() {

        progressBar = findViewById(R.id.progress);
        iv_edit_name = findViewById(R.id.iv_edit_name);
        tv_u_name = findViewById(R.id.tv_u_name);
        tv_address = findViewById(R.id.tv_address);
        tv_no = findViewById(R.id.tv_no);
        iv_edit_add = findViewById(R.id.iv_edit_add);
        tv_name = findViewById(R.id.tv_name);
        tv_email = findViewById(R.id.tv_email);
        tv_dob = findViewById(R.id.tv_dob);
        ll_mains = findViewById(R.id.ll_mains);
        tv_mob_no = findViewById(R.id.tv_mob_no);
        iv_edit_profile = findViewById(R.id.iv_edit_profile);
        imageView = findViewById(R.id.iv_profile);
        loader = new Loader(this);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissionsList, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissionsList, grantResults);
        imagePicker.handlePermission(requestCode, grantResults);
        if (PermissionHelper.handlePermission(requestCode, permissionsList, grantResults))
            imagePicker.choosePicture(true /*show camera intents*/);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.handleActivityResult(resultCode, requestCode, data);
        Uri uri = ImagePickerHelper.handleActivityResult(requestCode, resultCode, data);
        if (uri != null) {
            imageView.setImageURI(null);
            imageView.setImageURI(uri);

            imageRequest = MediaManager.get().upload(uri)
                    .option("public_id", reference.getKey())
                    .option("folder", "E-commerce_Admin/Profile/");

            if (imageRequest != null) {
                imageRequest.callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        // your code here
                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        // example code starts here
                        Double progress = (double) bytes / totalBytes;
                        // post progress to app UI (e.g. progress bar, notification)
                        // example code ends here
                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        // your code here
                        loader.show();

                        Log.i("gdggdg", "onSuccess: " + requestId);
                        Log.i("sfddgd", "onSuccess: " + resultData.toString());
                        Map<String, Object> map = new HashMap<>();

                        map.put(FirebaseConstants.SuperCategory.image, resultData.get("secure_url"));
                        map.put(FirebaseConstants.SuperCategory.image_format, resultData.get("format"));

                        FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.User.key)
                                .child(FirebaseAuth.getInstance().getUid())
                                .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                loader.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                loader.dismiss();
                            }
                        });

                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        // your code here
                        Log.i("fdgdff", "onError: " + error.getDescription());
                        loader.dismiss();
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        // your code here
                        Log.i("sdhfbdfed", "onSuccess: " + error.getDescription());
                    }
                })
                        .dispatch();
            }


        }
    }


    private Uri getTempUri() {
        String dri = Environment.getExternalStorageDirectory() + File.separator + "Temp";
        File dirFile = new File(dri);
        dirFile.mkdir();
        String file = dri + File.separator + "temp.png";
        File tempFile = new File(file);
        try {
            tempFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Uri.fromFile(tempFile);
    }
    private void showUi() {
        CURRENT_API_CALL++;
        if(CURRENT_API_CALL==TOTAL_API_CALL){
            ll_mains.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }


}
