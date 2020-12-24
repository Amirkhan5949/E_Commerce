package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.ui.fragment.SIGNINFragment;

public class DemoActivity extends AppCompatActivity {

   private Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

       Button ok= findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {

                    Intent intent = new Intent(getApplicationContext(),
                            MainActivity.class);
                    Pair[] pairs = new Pair[1];
                    pairs[0] = new Pair<View, String>(findViewById(R.id.ok), "transition_login");
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
                    { ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DemoActivity.this, pairs);
                        startActivity(intent, options.toBundle()); }
                    else { startActivity(intent); }
                }
            }
        });
    }


}