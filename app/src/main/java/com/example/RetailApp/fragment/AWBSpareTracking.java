package com.example.RetailApp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.RetailApp.R;
import com.example.RetailApp.entity.EOSpare;
import com.example.RetailApp.entity.EOSpareTrack;
import com.example.RetailApp.entity.EOTrack;

import org.json.JSONObject;

import java.util.ArrayList;

import helper.BMAGson;
import helper.HttpRequest;

public class AWBSpareTracking extends BMAFragment {


    LinearLayout trackRowMainLayout,awbstatusLayout;
    ArrayList<EOSpareTrack> spareTrackList = new ArrayList<>();
    EOSpare eoSpare;
    TextView status,noDataToDisplay;
    LayoutInflater inflater;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.eoSpare = getArguments().getParcelable("spareTrack");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.spare_track, container, false);
        this.inflater = inflater;
        this.trackRowMainLayout = this.view.findViewById(R.id.trackRowMainLayout);
        TextView awbnumber = this.view.findViewById(R.id.awbnumber);
        awbnumber.setText(eoSpare.awb_by_partner);
        TextView shippedVia = this.view.findViewById(R.id.shippedVia);
        shippedVia.setText(eoSpare.courier_name_by_partner);
        this.status = this.view.findViewById(R.id.status);
        this.noDataToDisplay=this.view.findViewById(R.id.noDataToDisplay);
        awbstatusLayout=this.view.findViewById(R.id.awbstatusLayout);
        getTrackData();
//        ArrayList<String> slist = new ArrayList<>();
//        slist.add("a");
//        slist.add("b");
//        slist.add("c");
//        slist.add("d");
//        int counter=1;
//        for (String s : slist) {
//            View itemView = inflater.inflate(R.layout.spare_track_row, null, false);
//            View sideColorBar=itemView.findViewById(R.id.sideColorBar);
//            this.trackRowMainLayout.addView(itemView);
//            if(counter==slist.size()){
//                sideColorBar.setVisibility(View.GONE);
//            }
//            counter++;
//        }

        return this.view;
    }

    HttpRequest httpRequest;

    private void getTrackData() {
        httpRequest = new HttpRequest(getMainActivity(), true);
        httpRequest.delegate = AWBSpareTracking.this;
//        SharedPreferences sharePrefrence= MainActivityHelper.applicationHelper().getSharedPrefrences(BMAConstants.LOGIN_INFO);
//        String entityID=sharePrefrence.getString("entityID", null);
//        String entityType=sharePrefrence.getString("entityType", null);
//        this.actionID = "searchData";
        //  Log.d("aaaaaaa"," All Task engineerID = "+MainActivityHelper.applicationHelper().getSharedPrefrences().getString("engineerID","abcfegd")+"    service id = "+MainActivityHelper.applicationHelper().getSharedPrefrences().getString("service_center_id", null));
        httpRequest.execute("getTrackingData",eoSpare.courier_name_by_partner, eoSpare.awb_by_partner);//,batteryLevel+"");"trackon","13005545");

    }



    @Override
    public void processFinish(String response) {
        Log.d("aaaaaa", "resssponse  = " + response);
        this.httpRequest.progress.dismiss();
        if (response.contains("data")) {
            JSONObject jsonObjectHttpReq;

            try {
                jsonObjectHttpReq = new JSONObject(response);

                final JSONObject jsonObject = jsonObjectHttpReq.getJSONObject("data");
                String statusCode = jsonObject.getString("code");
                if (statusCode.equals("0000")) {
                    JSONObject responseData = new JSONObject(jsonObject.getString("response"));

                    JSONObject data = new JSONObject(responseData.getString("data"));
                    if(data!=null && data.getString("items")!=null) {
                        spareTrackList = BMAGson.store().getList(EOSpareTrack.class, data.getString("items"));
                        bindDataToView();
                    }else {
                        noDataToDisplay.setVisibility(View.VISIBLE);
                        awbstatusLayout.setVisibility(View.GONE);
                    }
                }else {
                    noDataToDisplay.setVisibility(View.VISIBLE);
                    awbstatusLayout.setVisibility(View.GONE);
                }
            } catch (Exception e) {

            }
        }else {
            noDataToDisplay.setVisibility(View.VISIBLE);
            awbstatusLayout.setVisibility(View.GONE);
        }

    }

    private void bindDataToView() {
        int counter=1;
        this.status.setText(this.spareTrackList.get(0).status);
        int sizeList=this.spareTrackList.get(0).origin_info.trackinfo.size();
        if(sizeList==0){
            noDataToDisplay.setVisibility(View.VISIBLE);
            awbstatusLayout.setVisibility(View.GONE);
            return;
        }
      //  for(EOTrack eoTrack:this.spareTrackList.get(0).origin_info.trackinfo) {
        for(int i=sizeList;i>0;i--){
        EOTrack eoTrack=this.spareTrackList.get(0).origin_info.trackinfo.get(i-1);
            View itemView = inflater.inflate(R.layout.spare_track_row, null, false);
            View sideColorBar = itemView.findViewById(R.id.sideColorBar);
            ImageView roundImage=itemView.findViewById(R.id.roundImage);
            TextView date=itemView.findViewById(R.id.date);
            date.setText(eoTrack.Date);
            TextView statusText=itemView.findViewById(R.id.statusText);
            statusText.setText(eoTrack.StatusDescription+eoTrack.Details);

          //  if(i==sizeList){
                sideColorBar.setBackgroundColor(getResources().getColor(R.color.green_light_color));
               // roundImage.setTextColor(getResources().getColor(R.color.green_light_color));
               // date.setTextColor(getResources().getColor(R.color.green_light_color));
            //}else{
            //yaha
//                if(i!=1){
//                    roundImage.setBackground(getResources().getDrawable(R.drawable.round_gray_icon));
//                }
//           // }
//            if (i == 1 &&  eoTrack.checkpoint_status!=null && eoTrack.checkpoint_status.toLowerCase().contains("delivered")) {
//                sideColorBar.setVisibility(View.GONE);
//                date.setTextColor(getResources().getColor(R.color.green_light_color));
//                roundImage.setBackground(getResources().getDrawable(R.drawable.bus));
//              //  roundImage.setText(R.string.truck_icon);
//                //roundImage.setTextColor(getResources().getColor(R.color.green_light_color));
//
    //        }else
                if(i==1){
                sideColorBar.setVisibility(View.GONE);
            }
            //yaha tak
            if(eoTrack.ItemNode!=null){
                roundImage.setBackground(getResources().getDrawable(R.drawable.bus));
            }else{
                if(eoTrack.checkpoint_status!=null) {
                    switch (eoTrack.checkpoint_status){
                        case "delivered":
                            roundImage.setBackground(getResources().getDrawable(R.drawable.checked));
                            break;
                        case "pickup":
                        roundImage.setBackground(getResources().getDrawable(R.drawable.flag));
                        break;
                        default:
                            roundImage.setBackground(getResources().getDrawable(R.drawable.round_gray_icon));
                    }
                }
            }
            this.trackRowMainLayout.addView(itemView);
           // counter++;
        }
    }
}
