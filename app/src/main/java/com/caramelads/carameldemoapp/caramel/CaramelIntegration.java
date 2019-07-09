package com.caramelads.carameldemoapp.caramel;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.caramelads.sdk.CaramelAdListener;
import com.caramelads.sdk.ConsentDialog;


public class CaramelIntegration {
    private Context caramelContext;
    private Handler caramelHandler = new Handler();

    private static final int SDK_READY_TIME = 1_000;
    private static final int ADS_POST_TIME = 10_000;
    private static boolean adLoaded = false;
    private static boolean adFailed = false;

    public CaramelIntegration(Context context){
        caramelContext = context;
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
                caramelHandler.postDelayed(caramelRunnable, ADS_POST_TIME);
            }

            @Override
            public void adLoaded() {
                caramelHandler.postDelayed(caramelRunnable, ADS_POST_TIME);
                Log.d("###", "SDK is loaded");
            }

            @Override
            public void adOpened() {
                Log.d("###", "SDK is opened");
            }

            @Override
            public void adClicked() {
                Log.d("###", "SDK is clicked");
            }

            @Override
            public void adClosed() {
                caramelHandler.postDelayed(caramelRunnable, ADS_POST_TIME);
                Log.d("###", "SDK is closed");
            }

            @Override
            public void adFailed() {
                caramelHandler.postDelayed(caramelRunnable, ADS_POST_TIME);
                Log.d("###", "SDK is failed");
            }
        });
    }

    private Runnable caramelRunnable = new Runnable() {
        @Override
        public void run() {
            com.caramelads.sdk.CaramelAds.cache((Activity) caramelContext);
        }
    };

    static public void showAds(){
        if(com.caramelads.sdk.CaramelAds.isLoaded())
            com.caramelads.sdk.CaramelAds.show();
    }
}
