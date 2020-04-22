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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dealerapp.R;
import com.example.dealerapp.adapter.BMARecyclerAdapter;
import com.example.dealerapp.entity.EOBooking;
import com.example.dealerapp.entity.EOSpareDetails;

import java.util.ArrayList;

import util.BMAConstants;

//// this class uses to show spare status
public class SparePartStatus extends BMAFragment {

    public ArrayList<EOSpareDetails> spares = new ArrayList<>();
    RecyclerView spareRecyclerView;
    EOBooking eoBooking;
    LinearLayout sparerequstedbysfLayout, estimateGivenLayout, sparePartShippedLayout,
            defectiveSapreShippedbysfLayout, InvoiceIDLayout, OUWInvoiceDetailLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.eoBooking = getArguments().getParcelable("eoBooking");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.sparepart_tab_fragment, container, false);
        this.sparerequstedbysfLayout = this.view.findViewById(R.id.sparerequstedbysfLayout);
        this.estimateGivenLayout = this.view.findViewById(R.id.estimateGivenLayout);
        this.sparePartShippedLayout = this.view.findViewById(R.id.sparePartShippedLayout);
        this.defectiveSapreShippedbysfLayout = this.view.findViewById(R.id.defectiveSapreShippedbysfLayout);
        this.InvoiceIDLayout = this.view.findViewById(R.id.InvoiceIDLayout);
        this.OUWInvoiceDetailLayout = this.view.findViewById(R.id.OUWInvoiceDetailLayout);
        this.sparerequstedbysfLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(BMAConstants.HEADER_TXT, "Bookin View ");
                SpareRequestedBySF spareRequestedBySF = new SpareRequestedBySF();
                spareRequestedBySF.setArguments(bundle);
                getMainActivity().updateFragment(spareRequestedBySF, true);
            }
        });
        this.estimateGivenLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(BMAConstants.HEADER_TXT, "Bookin View ");
                SpareRequestedBySF spareRequestedBySF = new SpareRequestedBySF();
                spareRequestedBySF.setArguments(bundle);
                getMainActivity().updateFragment(spareRequestedBySF, true);
            }
        });
        this.sparePartShippedLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(BMAConstants.HEADER_TXT, "Bookin View ");
                SpareRequestedBySF spareRequestedBySF = new SpareRequestedBySF();
                spareRequestedBySF.setArguments(bundle);
                getMainActivity().updateFragment(spareRequestedBySF, true);
            }
        });
        this.defectiveSapreShippedbysfLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(BMAConstants.HEADER_TXT, "Bookin View ");
                SpareRequestedBySF spareRequestedBySF = new SpareRequestedBySF();
                spareRequestedBySF.setArguments(bundle);
                getMainActivity().updateFragment(spareRequestedBySF, true);
            }
        });
        this.InvoiceIDLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(BMAConstants.HEADER_TXT, "Bookin View ");
                SpareRequestedBySF spareRequestedBySF = new SpareRequestedBySF();
                spareRequestedBySF.setArguments(bundle);
                getMainActivity().updateFragment(spareRequestedBySF, true);
            }
        });
        this.OUWInvoiceDetailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(BMAConstants.HEADER_TXT, "Bookin View ");
                SpareRequestedBySF spareRequestedBySF = new SpareRequestedBySF();
                spareRequestedBySF.setArguments(bundle);
                getMainActivity().updateFragment(spareRequestedBySF, true);
            }
        });
        //  this.spareRecyclerView = this.view.findViewById(R.id.spareRecyclerView);
        // this.dataToView();
        return this.view;
    }

    private void dataToView() {
        BMARecyclerAdapter bmaRecyclerAdapter = new BMARecyclerAdapter(getContext(), getspareList(), spareRecyclerView, this, R.layout.spare_detail);
        spareRecyclerView.setHasFixedSize(true);
        spareRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        spareRecyclerView.setAdapter(bmaRecyclerAdapter);
    }

    private ArrayList<EOSpareDetails> getspareList() {
//        ArrayList<EOSpareDetails>spareList=new ArrayList<>();
//        spareList.add(new EOSpareDetails());
//        spareList.add(new EOSpareDetails());
//        spareList.add(new EOSpareDetails());
//        return spareList;
        return this.eoBooking != null ? this.eoBooking.spares : new ArrayList<EOSpareDetails>();
    }

    @Override
    public <T> void createRow(RecyclerView.ViewHolder viewHolder, View itemView, T rowObject, int position) {
        EOSpareDetails eoSpareDetails = (EOSpareDetails) rowObject;
        LinearLayout acknowledgeDateLayout = itemView.findViewById(R.id.acknowledgeDateLayout);
        TextView spareRequestedBy = itemView.findViewById(R.id.spareRequestedBy);
        TextView categoryAppliance = itemView.findViewById(R.id.categoryAppliance);
        TextView applianceModelNumber = itemView.findViewById(R.id.applianceModelNumber);
        TextView amount = itemView.findViewById(R.id.amount);
        TextView serilanumber = itemView.findViewById(R.id.serilanumber);
        TextView sparePartName = itemView.findViewById(R.id.sparePartName);
        TextView requestpartType = itemView.findViewById(R.id.requestpartType);
        TextView requestedquantity = itemView.findViewById(R.id.requestedquantity);
        TextView shipedquantity = itemView.findViewById(R.id.shipedquantity);
        TextView requesteddate = itemView.findViewById(R.id.requesteddate);
        // TextView requestedTime = itemView.findViewById(R.id.requestedTime);
        TextView acknowledgeDate = itemView.findViewById(R.id.acknowledgeDate);
        //TextView acknowledgeTime = itemView.findViewById(R.id.acknowledgeTime);
        TextView remarksByServiceCenter = itemView.findViewById(R.id.remarksByServiceCenter);
        //TextView symptom = itemView.findViewById(R.id.symptom);
        applianceModelNumber.setText(eoSpareDetails.model_number);
        amount.setText(eoSpareDetails.challan_approx_value + "  RS");
        serilanumber.setText(eoSpareDetails.serial_number);
        sparePartName.setText(eoSpareDetails.parts_shipped);
        requestpartType.setText(eoSpareDetails.shipped_parts_type);
        requestedquantity.setText(eoSpareDetails.quantity);
        shipedquantity.setText(eoSpareDetails.shipped_quantity);
        requesteddate.setText(eoSpareDetails.date_of_request);
        if (eoSpareDetails.date_of_acknowledge != null) {
            acknowledgeDate.setText(eoSpareDetails.date_of_acknowledge);


        } else {
            acknowledgeDate.setText("Not Available");
            //  acknowledgeDateLayout.setVisibility(View.GONE);
        }
        remarksByServiceCenter.setText(eoSpareDetails.remarks_by_sc);


        ;
    }
}
