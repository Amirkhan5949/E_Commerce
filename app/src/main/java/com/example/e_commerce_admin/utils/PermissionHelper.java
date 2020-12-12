package com.example.e_commerce_admin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class PermissionHelper {

    public static final int PERMISSION_ALL = 1234;

    public static final String[] PERMISSIONS = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
    };

    public static boolean setUpPermission(Activity context){
        if (!hasPermissions(context)) {
            ActivityCompat.requestPermissions(context, PERMISSIONS, PERMISSION_ALL);
            return false;
        } else {
            return true;
        }
    }

    private static boolean hasPermissions(Context context) {
        if (context != null && PERMISSIONS != null) {
            for (String permission : PERMISSIONS) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean handlePermission(int requestCode, String permissionsList[], int[] grantResults){
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
                        return true;
                    }
                    // Show permissionsDenied
                }
            }
        }

        return false;
    }

}
