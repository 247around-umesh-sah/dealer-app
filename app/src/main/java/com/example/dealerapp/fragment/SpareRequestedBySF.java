package com.example.dealerapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dealerapp.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class SpareRequestedBySF extends BMAFragment {

    LinearLayout mainLayout;
    ArrayList<String>spareRequestedBySfList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.sparetablayout, container, false);
        this.mainLayout=this.view.findViewById(R.id.mainLayout);
        bindData(inflater);
        return this.view;
    }
    private void bindData(@NonNull LayoutInflater inflater){
        View childView = inflater.inflate(R.layout.spare_requested, null, false);

        for(int i=0;i<spareRequestedBySfList.size();i++){
            bindData(childView);
            this.mainLayout.addView(childView);
        }


    }

    private void bindData(View itemView){

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
        TextView MoveToVendor=itemView.findViewById(R.id.MoveToVendor);
        TextView MoveToPartner=itemView.findViewById(R.id.MoveToPartner);





    }
}
