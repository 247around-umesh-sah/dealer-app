package com.example.dealerapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dealerapp.R;
import com.example.dealerapp.adapter.BMARecyclerAdapter;
import com.example.dealerapp.entity.EOBooking;
import com.example.dealerapp.entity.EOUnitDetail;

import java.util.ArrayList;

public class ApplianceDetails extends BMAFragment {
   // TextView brandName,capacityName,sfModelNumber,city,applianceModelNumber,amount, applianceSerialNumber,callType,time_slot;
    EOBooking eoBooking;
    RecyclerView applianceRecyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
         Log.d("aaaaa","eobboking =   "+getArguments().getParcelable("eoBooking"));

            this.eoBooking = getArguments().getParcelable("eoBooking");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.appliance, container, false);
        this.applianceRecyclerView= this.view.findViewById(R.id.applianceRecyclerView);
        this.dataToView();
        return this.view;
    }
    private void dataToView(){
        BMARecyclerAdapter bmaRecyclerAdapter = new BMARecyclerAdapter(getContext(),getApplianceList(), applianceRecyclerView, this, R.layout.appliance_detail);
        applianceRecyclerView.setHasFixedSize(true);
        applianceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        applianceRecyclerView.setAdapter(bmaRecyclerAdapter);
    }
    private ArrayList<EOUnitDetail> getApplianceList(){
        return this.eoBooking!=null ? this.eoBooking.unit_details : new ArrayList<EOUnitDetail>();
    }

    @Override
    public <T> void createRow(RecyclerView.ViewHolder viewHolder, View itemView, T rowObject, int position) {

        EOUnitDetail eoUnitDetail= (EOUnitDetail) rowObject;
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView capacityName=itemView.findViewById(R.id.capacityName);
        TextView sfModelNumber=itemView.findViewById(R.id.sfModelNumber);
        TextView city=itemView.findViewById(R.id.city);

        TextView applianceModelNumber=itemView.findViewById(R.id.applianceModelNumber);
        LinearLayout applianceModelNumberLayout=itemView.findViewById(R.id.applianceModelNumberLayout);

        TextView amount=itemView.findViewById(R.id.amount);
        TextView applianceSerialNumber=itemView.findViewById(R.id.applianceSerialNumber);
        TextView callType=itemView.findViewById(R.id.callType);
        TextView time_slot=itemView.findViewById(R.id.time_slot);
        EditText Problemdescriptionedittext=itemView.findViewById(R.id.Problemdescriptionedittext);
         brandName.setText(eoUnitDetail.appliance_brand);
         capacityName.setText(eoUnitDetail.appliance_capacity);
         sfModelNumber.setText(eoUnitDetail.sf_model_number);
         if(eoUnitDetail.model_number!=null) {
             applianceModelNumber.setText(eoUnitDetail.model_number);
         }else {
             applianceModelNumber.setText("Not Available");
             //applianceModelNumberLayout.setVisibility(View.GONE);
         }
        amount.setText(eoUnitDetail.customer_total+"  RS");
        applianceSerialNumber.setText(eoUnitDetail.serial_number);
        callType.setText(eoUnitDetail.price_tags);
        time_slot.setText(eoBooking.bookingTimeSlot);
        if(eoUnitDetail.appliance_description!=null)
        Problemdescriptionedittext.setText(eoUnitDetail.appliance_description);
        if(eoBooking.city!=null){
            city.setText(eoBooking.city);
        }else {
            city.setText("Not Available");
           // itemView.findViewById(R.id.cityLayout).setVisibility(View.GONE);
        }





    }
}
