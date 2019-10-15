package com.caramelads.carameldemoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.caramelads.carameldemoapp.caramel.CaramelIntegration;
import com.caramelads.sdk.CaramelAdListener;
import com.caramelads.sdk.ConsentDialog;

public class SplashActivity extends AppCompatActivity {

    private Handler caramelHandler = new Handler();
    private Context caramelContext = this;

    private static final int SDK_READY_TIME = 1_000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_act);
        setupCaramel();
    }

    private void setupCaramel(){
        com.caramelads.sdk.CaramelAds.initialize(caramelContext);
        com.caramelads.sdk.CaramelAds.setAdListener(new CaramelAdListener() {
            @Override
            public void sdkReady() {
                ConsentDialog consentDialog = new ConsentDialog.Builder().withContext(caramelContext).ifNeeded().build();
                consentDialog.show();
                caramelHandler.postDelayed(caramelRunnable, SDK_READY_TIME);
                Log.d("###", "SDK is ready");
            }

            @Override
            public void sdkFailed() {
                Log.d("###", "SDK is failed");
                startActivity(new Intent(caramelContext, TestActivity.class));
                finish();
            }

            @Override
            public void adLoaded() {
                Log.d("###", "SDK is loaded");
                startActivity(new Intent(caramelContext, TestActivity.class));
                CaramelIntegration.showAds();
                finish();
            }

            @Override
            public void adOpened() {
                Log.d("###", "AD is opened");

            }

            @Override
            public void adClicked() {
                Log.d("###", "AD is clicked");
            }

            @Override
            public void adClosed() {
                Log.d("###", "AD is closed");
            }

            @Override
            public void adFailed() {
                Log.d("###", "Ad is failed");
                startActivity(new Intent(caramelContext, TestActivity.class));
                finish();
            }
        });
    }

    private Runnable caramelRunnable = new Runnable() {
        @Override
        public void run() {
            com.caramelads.sdk.CaramelAds.cache((Activity) caramelContext);
        }
    };
}
