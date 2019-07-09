package com.caramelads.carameldemoapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.caramelads.carameldemoapp.caramel.CaramelIntegration;


public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test);
//        CaramelIntegration.showAds();
        CaramelIntegration caramelIntegration = new CaramelIntegration(this);
        Button button = findViewById(R.id.btn_start_next_act);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestActivity.this, AnotherActivity.class));
                CaramelIntegration.showAds();
            }
        });
    }


}
