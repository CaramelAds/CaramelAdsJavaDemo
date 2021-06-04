package com.caramelads.carameldemoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.caramelads.carameldemoapp.caramel.CaramelIntegration;

public class AnotherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_act);

        // back to main activity
        findViewById(R.id.goBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // show caramel ads
        CaramelIntegration.showAds();
    }
}
