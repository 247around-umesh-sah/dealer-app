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

public class DefectiveSparePartShippedFragment extends BMAFragment {

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
        this.view = inflater.inflate(R.layout.defective_spare_part, container, false);
        this.mainLayout=this.view.findViewById(R.id.mainLayout);
        bindData(this.view);
        return this.view;
    }


    private void bindData(View itemView){
      //  this.partTrack=itemView.findViewById(R.id.partTrack);
        TextView spareID=itemView.findViewById(R.id.spareID);
//        TextView partShippedBy=itemView.findViewById(R.id.partShippedBy);
//        partShippedBy.setText("SF Dispatch Defective Part To");

        TextView partnerWarehouse=itemView.findViewById(R.id.sfDispatechDefectivepart);
        TextView shippedParts=itemView.findViewById(R.id.shippedParts);
        TextView shippedPartsNumber=itemView.findViewById(R.id.shippedPartsNumber);
        //TextView partType=itemView.findViewById(R.id.partType);
        TextView shippedQuantity=itemView.findViewById(R.id.shippedQuantity);
     //   TextView PickupRequest=itemView.findViewById(R.id.PickupRequest);
      //  TextView PickupSchedule=itemView.findViewById(R.id.PickupSchedule);
        TextView courierName=itemView.findViewById(R.id.courierName);
        TextView AWB=itemView.findViewById(R.id.AWB);
        TextView noOfBoxes=itemView.findViewById(R.id.NoOfBoxes);

        TextView shippeddate=itemView.findViewById(R.id.shippeddate);
       // TextView EDD=itemView.findViewById(R.id.EDD);
       // itemView.findViewById(R.id.eddLayout).setVisibility(View.GONE);
        TextView RemarksByPartner=itemView.findViewById(R.id.RemarksByPartner);
        TextView RemarksBySF=itemView.findViewById(R.id.RemarksBySF);
        TextView courierCharge=itemView.findViewById(R.id.courierCharge);
        TextView weight=itemView.findViewById(R.id.Weight);

        TextView sfChallanNumber=itemView.findViewById(R.id.sfChallanNumber);
     //   TextView ChallanapproxValue=itemView.findViewById(R.id.ChallanapproxValue);
        TextView IsDefectiveOkPartsRequired=itemView.findViewById(R.id.IsDefectiveOkPartsRequired);
        spareID.setText(eoSpare.id!=null ? eoSpare.id : "Not Available");
        partnerWarehouse.setText(eoSpare.send_defective_to);
        shippedParts.setText(eoSpare.defective_part_shipped);
        shippedPartsNumber.setText(eoSpare.shipped_part_number!=null ? eoSpare.shipped_part_number : "Not Available");
      //  partType.setText(eoSpare.shipped_parts_type);
        shippedQuantity.setText(eoSpare.shipped_quantity);
        courierName.setText(eoSpare.courier_name_by_sf);
        AWB.setText(eoSpare.awb_by_sf);
        weight.setText(eoSpare.sf_billable_weight!=null ? eoSpare.sf_billable_weight : "Not Available");
        courierCharge.setText(eoSpare.courier_charges_by_sf!=null ? eoSpare.courier_charges_by_sf+"  Rs" : "Not Available");
        shippeddate.setText(eoSpare.defective_part_shipped_date);
       // EDD.setText(eoSpare.edd!=null ? eoSpare.edd : "Not Available");
        noOfBoxes.setText(eoSpare.sf_box_count);
        RemarksBySF.setText(eoSpare.remarks_defective_part_by_sf);
        RemarksByPartner.setText(eoSpare.remarks_defective_part_by_partner!=null ? eoSpare.remarks_defective_part_by_partner : "Not Available");
        sfChallanNumber.setText(eoSpare.sf_challan_number!=null ? eoSpare.sf_challan_number :"Not Available");
       // ChallanapproxValue.setText(eoSpare.challan_approx_value);
      //  this.partTrack.setVisibility(View.GONE);


    }
}
