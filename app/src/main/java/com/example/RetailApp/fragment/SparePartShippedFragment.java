package com.example.RetailApp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.RetailApp.R;
import com.example.RetailApp.entity.EOSpare;

import java.util.ArrayList;

import util.BMAConstants;

public class SparePartShippedFragment extends BMAFragment {
    ArrayList<String>sparePartShippedList=new ArrayList<>();
    LinearLayout mainLayout,partTrack;
    EOSpare eoSpare;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.eoSpare=getArguments().getParcelable("spareShipped");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.spare_part_shipped, container, false);
        this.mainLayout=this.view.findViewById(R.id.mainLayout);
        bindData(this.view);
        return this.view;
    }


    private void bindData(View itemView){
        this.partTrack=itemView.findViewById(R.id.partTrack);
        TextView spareID=itemView.findViewById(R.id.spareID);
        TextView partnerWarehouse=itemView.findViewById(R.id.partner_warehouse);
        TextView shippedParts=itemView.findViewById(R.id.shippedParts);
        TextView shippedPartsNumber=itemView.findViewById(R.id.shippedPartsNumber);
        TextView partType=itemView.findViewById(R.id.partType);
        TextView shippedQuantity=itemView.findViewById(R.id.shippedQuantity);
        TextView PickupRequest=itemView.findViewById(R.id.PickupRequest);
        TextView PickupSchedule=itemView.findViewById(R.id.PickupSchedule);
        TextView courierName=itemView.findViewById(R.id.courierName);
        TextView AWB=itemView.findViewById(R.id.AWB);
        TextView noOfBoxes=itemView.findViewById(R.id.NoOfBoxes);
        TextView weight=itemView.findViewById(R.id.Weight);
        TextView shippeddate=itemView.findViewById(R.id.shippeddate);
        TextView EDD=itemView.findViewById(R.id.EDD);
        TextView RemarksByPartner=itemView.findViewById(R.id.RemarksByPartner);
        TextView ChallanNumber=itemView.findViewById(R.id.ChallanNumber);
        TextView ChallanapproxValue=itemView.findViewById(R.id.ChallanapproxValue);
        TextView IsDefectiveOkPartsRequired=itemView.findViewById(R.id.IsDefectiveOkPartsRequired);
        spareID.setText(eoSpare.id!=null ? eoSpare.id : "Not Available");
        partnerWarehouse.setText("Not Available");
        shippedParts.setText(eoSpare.parts_shipped);
        shippedPartsNumber.setText(eoSpare.shipped_part_number!=null ? eoSpare.shipped_part_number : "Not Available");
        partType.setText(eoSpare.shipped_parts_type);
        shippedQuantity.setText(eoSpare.shipped_quantity);
        courierName.setText(eoSpare.courier_name_by_partner!=null ? eoSpare.courier_name_by_partner :"Not Available");
        AWB.setText(eoSpare.awb_by_partner!=null ? eoSpare.awb_by_partner :  "Not Available");
        weight.setText("Not Available");
        shippeddate.setText(eoSpare.shipped_date);
        EDD.setText(eoSpare.edd!=null ? eoSpare.edd : "Not Available");
        RemarksByPartner.setText(eoSpare.remarks_by_partner!=null ? eoSpare.remarks_by_partner : "Not Available");
        ChallanNumber.setText(eoSpare.partner_challan_number!=null ? eoSpare.partner_challan_number :"Not Available");
        ChallanapproxValue.setText(eoSpare.challan_approx_value+" Rs");
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
        bundle.putParcelable("spareTrack", eoSpare);
        AWBSpareTracking spareRequestedBySF = new AWBSpareTracking();
        spareRequestedBySF.setArguments(bundle);
        getMainActivity().updateFragment(spareRequestedBySF, true);
    }
}
