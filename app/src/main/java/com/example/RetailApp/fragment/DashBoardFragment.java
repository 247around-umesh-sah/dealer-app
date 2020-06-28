package com.example.RetailApp.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.RetailApp.R;
import com.example.RetailApp.component.BMAAlertDialog;
import com.example.RetailApp.entity.EOBooking;

import org.json.JSONObject;

import java.util.ArrayList;

import helper.BMAGson;
import helper.HttpRequest;
import helper.MainActivityHelper;
import util.BMAConstants;
import util.BMAUIUtil;

public class DashBoardFragment extends BMAFragment implements View.OnClickListener {
    ProgressBar progressBar;
    LinearLayout D0Layout,D1Layout,D2Layout,D3Layout;
    View d0Devider,d1Devider,d2Devider,d3Devider;
    ImageView searchIcon;
    EditText searchField;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view=inflater.inflate(R.layout.dashboard_fragment,container,false);
        this.progressBar=this.view.findViewById(R.id.incentiveProgressBar);
        this.searchIcon=this.view.findViewById(R.id.searchIcon);
        this.searchField=this.view.findViewById(R.id.serachField);
        D0Layout=this.view.findViewById(R.id.D0Layout);
        D1Layout=this.view.findViewById(R.id.D1Layout);
        D2Layout=this.view.findViewById(R.id.D2Layout);
        D3Layout=this.view.findViewById(R.id.D3Layout);
        this.d0Devider=this.view.findViewById(R.id.d0Devider);
        this.d1Devider=this.view.findViewById(R.id.d1Devider);
        this.d2Devider=this.view.findViewById(R.id.d2Devider);
        this.d3Devider=this.view.findViewById(R.id.d3Devider);
        this.d0Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.lightgreen));
        this.progressBar.setProgress(70);
        D0Layout.setOnClickListener(this);
        D1Layout.setOnClickListener(this);
        D2Layout.setOnClickListener(this);
        D3Layout.setOnClickListener(this);
        this.searchIcon.setOnClickListener(this);

        return this.view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.D0Layout:
                this.d0Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.lightgreen));
                this.d1Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.gray));
                this.d2Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.gray));
                this.d3Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.gray));
                break;
            case R.id.D1Layout:
                this.d1Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.lightgreen));
                this.d0Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.gray));
                this.d2Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.gray));
                this.d3Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.gray));
                this.progressBar.setProgress(90);
                break;
            case R.id.D2Layout:
                this.d2Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.lightgreen));
                this.d1Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.gray));
                this.d0Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.gray));
                this.d3Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.gray));
                this.progressBar.setProgress(40);
                break;
            case R.id.D3Layout:
                this.d3Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.lightgreen));
                this.d1Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.gray));
                this.d2Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.gray));
                this.d0Devider.setBackgroundColor(BMAUIUtil.getColor(R.color.gray));
                this.progressBar.setProgress(50);
                break;
            case R.id.searchIcon:
                if(checkFieldEmpty()) {
                    getSearchedData();
                }
        }
    }

private boolean checkFieldEmpty(){

        if(this.searchField.getText().toString().trim().length()>0){

            return true;
        }
    Toast.makeText(getContext(), "Field can't be blank", Toast.LENGTH_SHORT).show();
        return false;
}
    HttpRequest httpRequest;

    private void getSearchedData() {
        httpRequest = new HttpRequest(getMainActivity(), true);
        httpRequest.delegate = DashBoardFragment.this;
        SharedPreferences sharePrefrence= MainActivityHelper.applicationHelper().getSharedPrefrences(BMAConstants.LOGIN_INFO);
        String entityID=sharePrefrence.getString("entityID", null);
        String entityType=sharePrefrence.getString("entityType", null);
         this.actionID = "searchData";
        //  Log.d("aaaaaaa"," All Task engineerID = "+MainActivityHelper.applicationHelper().getSharedPrefrences().getString("engineerID","abcfegd")+"    service id = "+MainActivityHelper.applicationHelper().getSharedPrefrences().getString("service_center_id", null));
        httpRequest.execute("searchData", entityID,entityType, this.searchField.getText().toString().trim(), getMainActivity().getPinCode());//,batteryLevel+"");

    }
    String actionID;

    @Override
    public void processFinish(String response) {
        super.processFinish(response);
        Log.d("bbbbbbb", "response  = " + response);


        httpRequest.progress.dismiss();
        if (response.contains("data")) {
            JSONObject jsonObjectHttpReq;

            try {
                jsonObjectHttpReq = new JSONObject(response);

                final JSONObject jsonObject = jsonObjectHttpReq.getJSONObject("data");
                String statusCode = jsonObject.getString("code");
                if (statusCode.equals("0000")) {
                    //  JSONObject responseData = new JSONObject(jsonObject.getString("response"));

                        ArrayList<EOBooking> searchedBookingList = BMAGson.store().getList(EOBooking.class, jsonObject.getJSONObject("response").getString("Bookings"));
                        openSearchedBookingPage(searchedBookingList);


                } else if (this.actionID.equalsIgnoreCase("searchData")) {
                    //  if(statusCode.equals("0061")){
                    BMAAlertDialog bmaAlertDialog = new BMAAlertDialog(getContext(), false, true) {


                        @Override
                        public void onWarningDismiss() {
                            super.onWarningDismiss();
                        }
                    };
                    String result = jsonObject.getString("result");
                    if (result == null || result.length() == 0) {
                        result = "Data not found";
                    }
                    bmaAlertDialog.show(result);
                    //   }
                }
            } catch (Exception e) {

            }
        }else{
            BMAAlertDialog bmaAlertDialog = new BMAAlertDialog(getContext(), false, true) {


                @Override
                public void onWarningDismiss() {
                    super.onWarningDismiss();
                }
            };

            bmaAlertDialog.show("Server Error");
        }
    }
    private void openSearchedBookingPage(ArrayList<EOBooking> searchedBookingList) {
        // searchedBookingList
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("searchedBookingList", searchedBookingList);
        SearchedBookingFragment searchedBookingFragment = new SearchedBookingFragment();
        bundle.putString(BMAConstants.HEADER_TXT, "Searched Bookings");
        bundle.putString("filterStr", searchField.getText().toString().trim());
        searchedBookingFragment.setArguments(bundle);
        searchField.setText("");
        getMainActivity().updateFragment(searchedBookingFragment, true);

    }
}
