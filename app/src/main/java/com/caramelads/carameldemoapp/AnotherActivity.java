package com.caramelads.carameldemoapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.caramelads.carameldemoapp.caramel.CaramelApp;
import com.caramelads.carameldemoapp.caramel.CaramelIntegration;

public class AnotherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_act);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CaramelIntegration.showAds();
    }
}
