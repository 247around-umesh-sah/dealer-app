package com.example.RetailApp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.RetailApp.entity.EOLogin;

import org.json.JSONException;
import org.json.JSONObject;

import helper.ApiResponse;
import helper.BMAGson;
import helper.ConnectionDetector;
import helper.HttpRequest;
import helper.MainActivityHelper;
import helper.Misc;
import util.BMAConstants;
import util.BMAUIUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ApiResponse {

    Button login_button;
    EditText phonenumber, password;
    EOLogin eoLogin;
    RelativeLayout loginLayout;

    public ConnectionDetector cd;
    public Misc misc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        cd = new ConnectionDetector(this);
        misc = new Misc(this);
        misc.checkAndLocationRequestPermissions();
        if(isDealerLogin()){
            openMainActivity();
        }else {
            showLoginView();
        }


    }
    // in this method checked delaer is already login or not
    private boolean isDealerLogin(){
        SharedPreferences sharePrefrence= MainActivityHelper.applicationHelper().getSharedPrefrences(BMAConstants.LOGIN_INFO);
        if(sharePrefrence!=null) {
            String entityID = sharePrefrence.getString("entityID", null);
            if(entityID!=null){
                return true;
            }
        }
        return false;
    }
    // this method is used to show login view
    private void showLoginView(){
        this.loginLayout.setVisibility(View.VISIBLE);
    }

    // initialize view in this method
    @SuppressLint("ResourceType")
    private void init() {
        this.loginLayout=findViewById(R.id.loginLayout);
      //  this.loginLayout.setVisibility(View.GONE);
        this.login_button = findViewById(R.id.login_button);
        this.login_button.setOnClickListener(this);
        this.phonenumber = findViewById(R.id.phonenumber);
        this.password = findViewById(R.id.password);
        BMAUIUtil.setBackgroundRect(login_button, getResources().getColor(R.color.colorPrimaryDark), R.dimen._70dp);

        // openLoginActivity();
    }

    // use this method to open Main Activity
    private void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    HttpRequest httpRequest;

    // to bind request in this method
    private void getRequest() {
        Log.d("aaaaa", "before mobile getRequest");
        String mobileNo = phonenumber.getText().toString().trim();
        String Password = password.getText().toString().trim();
        Log.d("aaaaa", "getRequest");
        this.httpRequest = new HttpRequest(this, true);
        httpRequest.delegate = this;
        Log.d("aaaaa", "getRequestHTTPREQUEST done");
        this.httpRequest.execute("dealerLogin", mobileNo, Password);
        Log.d("aaaaa", "aftergetRequest");
    }

    // afetr click on login button this method execute send the erquest to server
    @Override
    public void onClick(View v) {
      //    openMainActivity();
        if (checkEmptyFeild()) {
            getRequest();
        }
    }

    // To check empty field
    private boolean checkEmptyFeild() {
        if (phonenumber.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Phone number can not be blank", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Password can not be blank", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    @Override
    public void processFinish(String httpReqResponse) {
        Log.d("aaaaa", "response = " + httpReqResponse);
        if (httpReqResponse.contains("data")) {
            JSONObject jsonObjectHttpReq;


            try {
                jsonObjectHttpReq = new JSONObject(httpReqResponse);


                final JSONObject jsonObject = jsonObjectHttpReq.getJSONObject("data");
                String statusCode = jsonObject.getString("code");
                if (statusCode.equals("0000")) {
                    httpRequest.progress.dismiss();

                    JSONObject response = new JSONObject(jsonObject.getString("response"));
                    this.eoLogin = BMAGson.store().getObject(EOLogin.class, response);
                    SharedPreferences.Editor editor = MainActivityHelper.applicationHelper().getSharedPrefrences(BMAConstants.LOGIN_INFO).edit();
                    editor.putString("entityID", response.getString("entity_id"));
                    editor.putString("entityType", response.getString("entity"));
                    editor.commit();
                    openMainActivity();


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
}
