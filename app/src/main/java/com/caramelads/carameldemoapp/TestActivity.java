package com.caramelads.carameldemoapp;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import com.caramelads.carameldemoapp.caramel.CaramelIntegration;

public class TestActivity extends AppCompatActivity {
    private RelativeLayout rootLayout;
    private Point screenSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test);

        initRes();
        int itemWidth=screenSize.x/5,itemHeight=screenSize.y/12;

        ImageView title=initImage((int)(itemWidth*3),(int)(itemHeight*2.5),R.drawable.logo);
        title.setTranslationX((screenSize.x/2)-(int)(itemWidth*3)/2);
        title.setTranslationY(screenSize.y/9);
        AlphaAnimation aan=new AlphaAnimation(0,1);
        aan.setDuration(2000);
        title.setAnimation(aan);
        rootLayout.addView(title);

        initCloud(itemWidth,itemHeight,R.drawable.cloudleft,1000,-itemWidth,(screenSize.x/6),0,0);
        initCloud(itemWidth,itemHeight,R.drawable.cloudright,1400,screenSize.x, screenSize.x/1.4f,0,0);
        initCloud(itemWidth,itemHeight,R.drawable.cloudleft,2000,-itemWidth,(screenSize.x/6), screenSize.y/2.8f, screenSize.y/2.8f);

        ImageView button=initImage(itemWidth,itemWidth,R.drawable.button);
        button.startAnimation(initButtonAnimation());
        button.setTranslationX((screenSize.x/2)-itemWidth/2.5f);
        button.setTranslationY(screenSize.y/3.8f);
        rootLayout.addView(button);

        // launch new activity on click to button at the center of screen
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestActivity.this, AnotherActivity.class));
                // show caramel ads
                CaramelIntegration.showAds();
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

}
