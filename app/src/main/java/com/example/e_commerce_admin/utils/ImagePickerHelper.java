package com.example.e_commerce_admin.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.myhexaville.smartimagepicker.ImagePicker;
import com.myhexaville.smartimagepicker.OnImagePickedListener;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class ImagePickerHelper {

    public  static ImagePicker getInstance(final Activity activity){
         return new ImagePicker(activity, /* activity non null*/
                  null, /* fragment nullable*/
                  new OnImagePickedListener() {
                      @Override
                      public void onImagePicked(Uri imageUri) {
                          UCrop.of(imageUri, getTempUri())
                                  .withAspectRatio(1, 1)
                                  .start(activity);
                      }
                  }

          );
      }

    public  static ImagePicker getInstance(final Activity activity, OnImagePickedListener onImagePickedListener){
        return new ImagePicker(activity, /* activity non null*/
                null, /* fragment nullable*/
                onImagePickedListener
        );
    }



    public static Uri getTempUri(){
        String dri = Environment.getExternalStorageDirectory()+ File.separator+"Temp";
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

    public static Uri getTempUri(int i){
        String dri = Environment.getExternalStorageDirectory()+ File.separator+"Temp";
        File dirFile = new File(dri);
        dirFile.mkdir();
        String file = dri+File.separator+"temp"+i+".png";
        File tempFile = new File(file);
        try {
            tempFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Uri.fromFile(tempFile);
    }

    public static Uri handleActivityResult(int requestCode, int resultCode, Intent data){

        if (data != null) {
            if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
                final Uri resultUri = UCrop.getOutput(data);
                Log.i("dsjknjsdkn", "onActivityResult: "+resultUri);
                return resultUri;
            } else if (resultCode == UCrop.RESULT_ERROR) {
                final Throwable cropError = UCrop.getError(data);
                Log.i("dsjknjsdkn", "onActivityResult: "+cropError.getMessage());
                return null;
            }
            else
                return null;
        }

        return null;

    }

}
