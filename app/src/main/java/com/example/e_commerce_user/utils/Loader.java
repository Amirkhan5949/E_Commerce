package com.example.e_commerce_user.utils;

import android.content.Context;
import android.view.Gravity;

import com.example.e_commerce_user.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class Loader {

    DialogPlus dialog;

    public Loader(Context context) {
        dialog = DialogPlus.newDialog(context)
                .setContentHolder(new ViewHolder(R.layout.loader))
                .setCancelable(false)
                .setGravity(Gravity.CENTER)
                .setContentBackgroundResource(R.color.transparent)
                .create();
    }

    public void show(){
        dialog.show();
    }

    public void dismiss(){
        if(dialog.isShowing())
        dialog.dismiss();
    }
}
