package com.caramelads.carameldemoapp.caramel;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.caramelads.sdk.CaramelAdListener;
import com.caramelads.sdk.CaramelAds;
import com.caramelads.sdk.ConsentDialog;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import br.com.simplepass.loading_button_lib.interfaces.OnAnimationEndListener;

/**
 * Created by Dmitriy on 02.03.2018.
 */

public class CaramelIntegration {
    private Context caramelContext;
    private final CircularProgressButton circularProgressButton;

    public CaramelIntegration(Context context, CircularProgressButton circularProgressButton){
        this.caramelContext = context;
        this.circularProgressButton = circularProgressButton;
        setupCaramel();
    }

    private void setupCaramel(){
        CaramelAds.initialize(caramelContext);
        CaramelAds.setAdListener(new CaramelAdListener() {
            @Override
            public void sdkReady() {
                ConsentDialog consentDialog = new ConsentDialog.Builder().withContext(caramelContext).ifNeeded().build();
                consentDialog.show();
                Log.d("###", "SDK is ready");
            }

            @Override
            public void sdkFailed() {
                buttonFlex("SDK is failed");
            }

            @Override
            public void adLoaded() {
                buttonFlex("Ads is loaded, click me");
                Log.d("###", "Ads is loaded");
            }

            @Override
            public void adOpened() {
                Log.d("###", "Ads is opened");
            }

            @Override
            public void adClicked() {
                Log.d("###", "Ads is clicked");
            }

            @Override
            public void adClosed() {
                buttonFlex("Ads is closed");
                Log.d("###", "Ads is closed");
            }

            @Override
            public void adFailed() {
                buttonFlex("Ads is failed, retry");
                Log.d("###", "Ads is failed");
            }
        });
    }

    private void buttonFlex(final String message){
        circularProgressButton.revertAnimation(new OnAnimationEndListener() {
            @Override
            public void onAnimationEnd() {
                circularProgressButton.setText(message);
            }
        });

    }

    public boolean cache(){
        CaramelAds.cache((Activity) caramelContext);
        return CaramelAds.isLoaded();
    }

    public void showAds(){
        if(CaramelAds.isLoaded())
            CaramelAds.show();
    }
}
