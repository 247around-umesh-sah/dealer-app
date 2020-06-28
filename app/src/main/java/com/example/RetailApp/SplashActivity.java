package com.example.RetailApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import helper.ApplicationHelper;
import helper.MainActivityHelper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        MainActivityHelper.setApplicationHelper(new ApplicationHelper(getApplicationContext()));
        getWindow().setBackgroundDrawable(null);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        openLoginActivity();
    }

    // to open login activity called this method
    private void openLoginActivity(){
        startActivity(new Intent(this,LoginActivity.class));
    }
}
