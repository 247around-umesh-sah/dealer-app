package com.example.dealerapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dealerapp.R;
import com.example.dealerapp.entity.EOBooking;

// this class uses to shoe booking detials
public class BookingDetails extends BMAFragment {

    EOBooking eoBooking;
    TextView customerName,address,bookingdate,closeddate,symptom,remarks,pinCode,amount,bookingID,status;
    LinearLayout closedbookindateLayout,symptomLayout,remarksLayout;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("aaaaa","eobboking =   "+getArguments().getParcelable("eoBooking"));
        this.eoBooking=getArguments().getParcelable("eoBooking");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.booking_details_row, container, false);
      this.init();
      this.dataToView();

        return this.view;
    }
    private void init(){
        this.customerName= this.view.findViewById(R.id.customerName);
        this.address= this.view.findViewById(R.id.address);
        this.bookingdate= this.view.findViewById(R.id.bookingdate);
        this.closeddate= this.view.findViewById(R.id.closeddate);
        this.symptom= this.view.findViewById(R.id.symptom);
        this.remarks= this.view.findViewById(R.id.remarks);
        this.pinCode=this.view.findViewById(R.id.pincode);
        this.amount=this.view.findViewById(R.id.amount);
        this.bookingID=this.view.findViewById(R.id.bookingID);
        this.status=this.view.findViewById(R.id.status);
        closedbookindateLayout=this.view.findViewById(R.id.closedbookindateLayout);
        symptomLayout=this.view.findViewById(R.id.symptomLayout);
        remarksLayout=this.view.findViewById(R.id.remarksLayout);
    }
    private void dataToView(){
        if(this.eoBooking!=null){
            this.bookingID.setText(eoBooking.bookingID);
            this.status.setText(eoBooking.current_status);
            this.customerName.setText(this.eoBooking.name);
            this.address.setText(this.eoBooking.bookingAddress);
            this.pinCode.setText(eoBooking.pincode);
            this.amount.setText(eoBooking.dueAmount+"  RS");
            this.bookingdate.setText(eoBooking.bookingDate);
            this.closeddate.setText(eoBooking.bookingDate);
            if (eoBooking.symptom!=null) {
                this.symptom.setText(eoBooking.symptom);
            }else{
                this.symptom.setText("Not Available");
            //    symptomLayout.setVisibility(View.GONE);
            }
            if(eoBooking.bookingRemarks!=null && eoBooking.bookingRemarks.trim().length()>0) {
                this.remarks.setText(eoBooking.bookingRemarks);
            }else{
                this.remarks.setText("Not Available");
               // this.remarksLayout.setVisibility(View.GONE);
            }

        }
    }
}
