package com.caramelads.carameldemoapp.caramel;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.caramelads.sdk.CaramelAdListener;
import com.caramelads.sdk.ConsentDialog;

import java.util.Random;


public class CaramelIntegration {
    private Context caramelContext;
    private Handler caramelHandler = new Handler();

    private static final int SDK_READY_TIME = 1_000;
    private static int ADS_POST_TIME;
    private static boolean first = true;
    private static boolean adLoaded = false;
    private static boolean adFailed = false;
    private static Random random;
    private static final int REQUEST_MIN_TIME = 150_000;
    private static final int REQUEST_MAX_TIME = 240_000;
    private static final int diff = REQUEST_MAX_TIME - REQUEST_MIN_TIME;

    public CaramelIntegration(Context context){
        caramelContext = context;
        setupCaramel();
        random = new Random();
    }

    public void setCaramelContext(Context caramelContext) {
        this.caramelContext = caramelContext;
    }

    private void setupCaramel(){
        com.caramelads.sdk.CaramelAds.initialize(caramelContext);
        com.caramelads.sdk.CaramelAds.setAdListener(new CaramelAdListener() {
            @Override
            public void sdkReady() {
                ConsentDialog consentDialog = new ConsentDialog.Builder().withContext(caramelContext).ifNeeded().build();
                consentDialog.show();
                if (!((Activity) caramelContext).isFinishing()) {
                    if (first) {
                        caramelHandler.postDelayed(caramelRunnable, SDK_READY_TIME);
                        first = false;
                    } else {
                        ADS_POST_TIME = random.nextInt(diff + 1 ) + REQUEST_MIN_TIME;
                        caramelHandler.postDelayed(caramelRunnable, ADS_POST_TIME);
                    }
                }
                Log.d("###", "SDK is ready");
            }

            @Override
            public void sdkFailed() {
                if (!((Activity) caramelContext).isFinishing() ) {
                    ADS_POST_TIME = random.nextInt(diff + 1 ) + REQUEST_MIN_TIME;
                    caramelHandler.postDelayed(caramelRunnable, ADS_POST_TIME);
                }
            }

            @Override
            public void adLoaded() {
                if (!((Activity) caramelContext).isFinishing() ) {
                    ADS_POST_TIME = random.nextInt(diff + 1 ) + REQUEST_MIN_TIME;
                    caramelHandler.postDelayed(caramelRunnable, ADS_POST_TIME);
                }
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
                Log.d("###", "SDK is closed");
            }

            @Override
            public void adFailed() {
                if (!((Activity) caramelContext).isFinishing() ) {
                    ADS_POST_TIME = random.nextInt(diff + 1 ) + REQUEST_MIN_TIME;
                    caramelHandler.postDelayed(caramelRunnable, ADS_POST_TIME);
                }
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
