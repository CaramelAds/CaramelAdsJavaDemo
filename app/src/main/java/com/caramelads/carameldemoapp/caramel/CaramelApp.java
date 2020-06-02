package com.caramelads.carameldemoapp.caramel;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CaramelApp extends Application implements Application.ActivityLifecycleCallbacks{

    private boolean first = true;
    private CaramelIntegration caramelIntegration;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);

    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        if (first) {
            caramelIntegration = new CaramelIntegration(activity);
            first = false;
        }
//        Log.e("TAG", "onActivityCreated: " + activity.getLocalClassName());
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        caramelIntegration.setCaramelContext(activity);
//        Log.e("TAG", "onActivityStarted: " + activity.getLocalClassName());
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
//        Log.e("TAG", "onActivityResumed: " + activity.getLocalClassName());
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
//        Log.e("TAG", "onActivityPaused: " + activity.getLocalClassName() );
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
//        Log.e("TAG", "onActivityStopped: " + activity.getLocalClassName());
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
//        Log.e("TAG", "onActivitySaveInstanceState: " + activity.getLocalClassName());
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
//        Log.e("TAG", "onActivityDestroyed: " + activity.getLocalClassName());
    }
}
