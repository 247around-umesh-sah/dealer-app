package com.example.RetailApp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.RetailApp.R;
import com.example.RetailApp.entity.EOSpare;

import java.util.ArrayList;

import util.BMAConstants;

public class SpareRequestedBySF extends BMAFragment {

    LinearLayout mainLayout,partTrack;
    ArrayList<String>spareRequestedBySfList=new ArrayList<>();
    EOSpare eoSpare;
    ImageView partDetailExpandIcon,dateExpandIcon,statusExpandIcon;
    CardView partDetialCardView,partDateCardView,partStatusCardView;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.eoSpare=getArguments().getParcelable("spareRequested");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.spare_requested, container, false);
       // this.mainLayout=this.view.findViewById(R.id.mainLayout);
        bindData(this.view);
        return this.view;
    }


    private void bindData(View itemView){
        this.partDetailExpandIcon=itemView.findViewById(R.id.partDetailExpandIcon);
        this.dateExpandIcon=itemView.findViewById(R.id.dateExpandIcon);
        this.statusExpandIcon=itemView.findViewById(R.id.statusExpandIcon);
        this.partDetialCardView=itemView.findViewById(R.id.partDetialCardView);
        this.partDateCardView=itemView.findViewById(R.id.partDateCardView);
        this.partStatusCardView=itemView.findViewById(R.id.partStatusCardView);
        this.partTrack=itemView.findViewById(R.id.partTrack);


        TextView spareID=itemView.findViewById(R.id.spareID);
        TextView partnerWarehouse=itemView.findViewById(R.id.partner_warehouse);
        TextView modelNumber=itemView.findViewById(R.id.modelnumber);
        TextView originalRequestedPart=itemView.findViewById(R.id.originalrequestedpart);
        TextView finalRequestedPart=itemView.findViewById(R.id.finalRequestedPart);
        TextView partsType=itemView.findViewById(R.id.partsType);
        TextView partsWarrantyStatus=itemView.findViewById(R.id.partsWarrantyStatus);
        TextView requestedQuantity=itemView.findViewById(R.id.requestedQuantity);
        TextView requestedDate=itemView.findViewById(R.id.requestedDate);
        TextView approvalDate=itemView.findViewById(R.id.approvalDate);
        TextView dateOfPurchase=itemView.findViewById(R.id.dateOfPurchase);
        TextView serialNumber=itemView.findViewById(R.id.serialNumber);
        TextView acknowledgeDateBYSF=itemView.findViewById(R.id.acknowledgeDateBYSF);
        TextView remarksBySC=itemView.findViewById(R.id.remarksBySC);
        TextView currentStatus=itemView.findViewById(R.id.currentStatus);
        TextView spareCancellationReason=itemView.findViewById(R.id.spareCancellationReason);
        TextView consumption=itemView.findViewById(R.id.Consumption);
        TextView consumptionReason=itemView.findViewById(R.id.consumptionReason);
        TextView consumptionRemarks=itemView.findViewById(R.id.ConsumptionRemarks);
//        TextView MoveToVendor=itemView.findViewById(R.id.MoveToVendor);
//        TextView MoveToPartner=itemView.findViewById(R.id.MoveToPartner);
        spareID.setText(this.eoSpare.id);
        partnerWarehouse.setText("Not Available");
        modelNumber.setText(eoSpare.model_number);
        originalRequestedPart.setText(eoSpare.original_parts_number);
        finalRequestedPart.setText("Not Available");
        partsType.setText(eoSpare.parts_requested_type);
        partsWarrantyStatus.setText(eoSpare.part_warranty_status);
        requestedQuantity.setText(eoSpare.quantity);
        requestedDate.setText(eoSpare.date_of_request);
        approvalDate.setText(eoSpare.spare_approval_date);
        dateOfPurchase.setText(eoSpare.date_of_purchase);
        serialNumber.setText(eoSpare.serial_number);
        if(eoSpare.acknowledge_date!=null)
        acknowledgeDateBYSF.setText(eoSpare.acknowledge_date);
        remarksBySC.setText(eoSpare.remarks_by_sc);
        currentStatus.setText(eoSpare.status);
        if(eoSpare.part_cancel_reason!=null)
        spareCancellationReason.setText(eoSpare.part_cancel_reason);
        consumption.setText(eoSpare.is_consumed);
        if(eoSpare.consumed_status!=null)
        consumptionReason.setText(eoSpare.consumed_status);
        if(eoSpare.consumption_remarks!=null)
        consumptionRemarks.setText(eoSpare.consumption_remarks);
        this.partDetailExpandIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(partDetialCardView.getVisibility()== View.VISIBLE){
                    partDetialCardView.setVisibility(View.GONE);
                }else {
                    partDetialCardView.setVisibility(View.VISIBLE);
                }
            }
        });
        this.dateExpandIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(partDateCardView.getVisibility()== View.VISIBLE){
                    partDateCardView.setVisibility(View.GONE);
                }else {
                    partDateCardView.setVisibility(View.VISIBLE);
                }
            }
        });
        this.statusExpandIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(partStatusCardView.getVisibility()== View.VISIBLE){
                    partStatusCardView.setVisibility(View.GONE);
                }else {
                    partStatusCardView.setVisibility(View.VISIBLE);
                }
            }
        });

this.partTrack.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        openTrackFragment();
    }
});



    }
    private void openTrackFragment(){
        Bundle bundle = new Bundle();
        bundle.putString(BMAConstants.HEADER_TXT, "Spare Track");
        //bundle.putParcelable("spareRequested", eoSpare);
        AWBSpareTracking spareRequestedBySF = new AWBSpareTracking();
        spareRequestedBySF.setArguments(bundle);
        getMainActivity().updateFragment(spareRequestedBySF, true);
    }
}
