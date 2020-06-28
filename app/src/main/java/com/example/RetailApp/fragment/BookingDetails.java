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
import com.example.RetailApp.entity.EOBooking;

// this class uses to show booking detials
public class BookingDetails extends BMAFragment {

    EOBooking eoBooking;
    TextView customerName, address, bookingdate, closeddate, symptom, remarks, pinCode, amount, bookingID,city, status,time_slot,mobileNumber,state,scheduleReason,cancelledReason;
    LinearLayout closedbookindateLayout, symptomLayout, remarksLayout, expandLayout;
    ImageView expandIcon;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.eoBooking = getArguments().getParcelable("eoBooking");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.booking_details_row, container, false);
        this.init();
        this.dataToView();

        return this.view;
    }

    private void init() {
        this.customerName = this.view.findViewById(R.id.customerName);
        this.address = this.view.findViewById(R.id.address);
        this.bookingdate = this.view.findViewById(R.id.bookingdate);
        this.closeddate = this.view.findViewById(R.id.closeddate);
        this.symptom = this.view.findViewById(R.id.symptom);
        this.remarks = this.view.findViewById(R.id.remarks);
        this.pinCode = this.view.findViewById(R.id.pincode);
        this.amount = this.view.findViewById(R.id.amountPaid);
        this.bookingID = this.view.findViewById(R.id.bookingID);
        this.status = this.view.findViewById(R.id.status);
        closedbookindateLayout = this.view.findViewById(R.id.closedbookindateLayout);
        symptomLayout = this.view.findViewById(R.id.symptomLayout);
        remarksLayout = this.view.findViewById(R.id.remarksLayout);
        this.expandIcon=this.view.findViewById(R.id.expandIcon);
        this.expandLayout=this.view.findViewById(R.id.expandLayout);
        time_slot=this.view.findViewById(R.id.time_slot);
        this.mobileNumber=this.view.findViewById(R.id.mobileNumber);
        this.state=this.view.findViewById(R.id.state);
        this.scheduleReason=this.view.findViewById(R.id.scheduleReason);
        this.cancelledReason=this.view.findViewById(R.id.cancelledReason);
        this.city=this.view.findViewById(R.id.city);



    }

    private void dataToView() {
        if (this.eoBooking != null) {
            this.bookingID.setText(eoBooking.bookingID);
            this.status.setText(eoBooking.current_status);
            this.customerName.setText(this.eoBooking.name);
            this.address.setText(this.eoBooking.bookingAddress);
            this.pinCode.setText(eoBooking.pincode);
            this.amount.setText(eoBooking.dueAmount + "  Rs");
            this.bookingdate.setText(eoBooking.bookingDate);
            this.closeddate.setText(eoBooking.bookingDate);
            time_slot.setText(eoBooking.bookingTimeSlot);
            if (eoBooking.symptom != null) {
                this.symptom.setText(eoBooking.symptom);
            } else {
                this.symptom.setText("Not Available");
                //    symptomLayout.setVisibility(View.GONE);
            }
            if (eoBooking.bookingRemarks != null && eoBooking.bookingRemarks.trim().length() > 0) {
                this.remarks.setText(eoBooking.bookingRemarks);
            } else {
                this.remarks.setText("Not Available");
                // this.remarksLayout.setVisibility(View.GONE);
            }
            if(eoBooking.primaryContact!=null){
                this.mobileNumber.setText(eoBooking.primaryContact);
            }
            if(eoBooking.state!=null){
                this.state.setText(eoBooking.state);
            }
            if(eoBooking.city!=null){
                city.setText(eoBooking.city);
            }

        }
        this.expandIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    expandLayout.setVisibility(expandLayout.getVisibility()==View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
    }
}
