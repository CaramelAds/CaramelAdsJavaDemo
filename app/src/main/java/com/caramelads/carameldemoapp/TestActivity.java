package com.caramelads.carameldemoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.caramelads.carameldemoapp.caramel.CaramelIntegration;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class TestActivity extends AppCompatActivity {
    private  CaramelIntegration caramelIntegration;
    private  CircularProgressButton buttonShowAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        initializeAds();
    }

    private void initialize() {
        setContentView(R.layout.activity_test);
        buttonShowAds = findViewById(R.id.adsButton);
        buttonShowAds.setText("CACHE");
        caramelIntegration = new CaramelIntegration(this, buttonShowAds);
    }

    private void initializeAds() {
        buttonShowAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonShowAds.startAnimation();
                buttonShowAds.clearComposingText();
                if (caramelIntegration.cache()){
                    buttonShowAds.revertAnimation();
                    caramelIntegration.showAds();
                }
            }
        });
    }
}
