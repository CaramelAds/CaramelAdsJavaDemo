package com.caramelads.carameldemoapp;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import com.caramelads.sdk.CaramelAdListener;
import com.caramelads.sdk.CaramelAds;

public class TestActivity extends AppCompatActivity {
    private RelativeLayout rootLayout;
    private Point screenSize;
    private ImageView button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test);
        //inittialize Caramel ADS
        CaramelAds.initialize(TestActivity.this);

        // event listener. You can set your own actions in response to events
        CaramelAds.setAdListener(new CaramelAdListener() {
            @Override
            public void sdkReady() {
                showToast("SDK READY","sdk is ready, wait while ad is load to cache and Caramel button is enable");
                //cache ads after CaramelSDK is ready
                CaramelAds.cache(TestActivity.this);
            }

            @Override
            public void sdkFailed() {
                showToast("SDK FAILED","sdk is failed");
            }

            @Override
            public void adLoaded() {
                showToast("AD LOADED","ad is loaded and you can push the Caramel button");
            }

            @Override
            public void adOpened() {
                showToast("AD OPENED","ad is opened");
            }

            @Override
            public void adClicked() {
                showToast("AD CLICKED","clicked on ad");
            }

            @Override
            public void adClosed() {
                showToast("AD CLOSED","ad is closed");
            }

            @Override
            public void adFailed() {
                showToast("AD FAILED","ad is failed");
            }
        });

        initRes();
        int itemWidth=screenSize.x/5,itemHeight=screenSize.y/12;

        ImageView title=initImage((int)(itemWidth*3),(int)(itemHeight*2.5),R.drawable.logo);
        title.setTranslationX((screenSize.x/2)-(int)(itemWidth*3)/2);
        title.setTranslationY(screenSize.y/9);
        AlphaAnimation aan=new AlphaAnimation(0,1);
        aan.setDuration(2000);
        title.setAnimation(aan);
        rootLayout.addView(title);

        initCloud(itemWidth,itemHeight,R.drawable.cloud,1000,-itemWidth,(screenSize.x/6),0,0);
        initCloud(itemWidth,itemHeight,R.drawable.cloud,1400,screenSize.x, screenSize.x/1.4f,0,0);
        initCloud(itemWidth,itemHeight,R.drawable.cloud,2000,-itemWidth,(screenSize.x/6), screenSize.y/2.8f, screenSize.y/2.8f);

        button=initImage(itemWidth,itemWidth,R.drawable.button);
        button.startAnimation(initButtonAnimation());
        button.setTranslationX((screenSize.x/2)-itemWidth/2.5f);
        button.setTranslationY(screenSize.y/3.8f);
        rootLayout.addView(button);

        // launch new activity on click to button at the center of screen
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show caramel ads if is loaded
                if(CaramelAds.isLoaded()) {
                    startActivity(new Intent(TestActivity.this, AnotherActivity.class));
                    CaramelAds.show();
                }
                else
                    showToast("WAIT","wait while ad is load to cache and Caramel button is enable");
            }
        });
    }

    private void initCloud(int width,int height,int id,int duration,float fromDX,float toDX,float fromDY,float toDY){
        ImageView cloud=initImage(width,height,id);
        cloud.setAnimation(initTranslateAnimation(fromDX,toDX,fromDY,toDY,duration));
        rootLayout.addView(cloud);
    }

    private void initRes(){
        rootLayout=findViewById(R.id.rootLayout);
        screenSize=new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
    }

    private ImageView initImage(int with,int height,int id){
        ImageView img=new ImageView(this);
        img.setBackgroundResource(id);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(with,height);
        img.setLayoutParams(layoutParams);
        return img;
    }

    private TranslateAnimation initTranslateAnimation(float from_dx,float to_dx,float from_dy,float to_dy,int duration){
        TranslateAnimation tr=new TranslateAnimation(from_dx,to_dx,from_dy,to_dy);
        tr.setDuration(duration);
        tr.setInterpolator(new LinearOutSlowInInterpolator());
        tr.setFillAfter(true);
        return tr;
    }

    private ScaleAnimation initButtonAnimation(){
        ScaleAnimation scaleIn=new ScaleAnimation(0.8f,1,0.8f,1, Animation.RELATIVE_TO_SELF, 2.5f,Animation.RELATIVE_TO_SELF, 2.5f);
        scaleIn.setDuration(600);
        scaleIn.setInterpolator(new DecelerateInterpolator());
        scaleIn.setRepeatCount(Animation.INFINITE);
        scaleIn.setRepeatMode(Animation.REVERSE);
        return scaleIn;
    }

    private void showToast(String title,String text){
        Toast toast=new Toast(getApplicationContext());
        toast.setGravity(Gravity.FILL_HORIZONTAL,0,0);
        toast.setDuration(Toast.LENGTH_LONG);

        GradientDrawable grad = new GradientDrawable();
        grad.setColor(0xffffffff);
        grad.setCornerRadius(6);
        grad.setStroke(2, 0xff000000);

        TextView tv=new TextView(this);
        tv.setText(Html.fromHtml("<b><font color=#f89406>"+title+":<br></font></b><br>"+text));
        tv.setTextSize(20);
        tv.setGravity(Gravity.CENTER);
        tv.setBackground(grad);

        toast.setView(tv);
        toast.show();
    }

}
