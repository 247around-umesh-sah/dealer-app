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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.RetailApp.R;
import com.example.RetailApp.adapter.BMARecyclerAdapter;
import com.example.RetailApp.entity.EOBooking;
import com.example.RetailApp.entity.EOSpare;
import com.example.RetailApp.entity.EOSpareDetails;
import com.example.RetailApp.entity.EOSparePartTabResponse;

import org.json.JSONObject;

import java.util.ArrayList;

import helper.BMAGson;
import helper.HttpRequest;
import util.BMAConstants;

//// this class uses to show spare status
public class SparePartStatus extends BMAFragment {

    public ArrayList<EOSpareDetails> spares = new ArrayList<>();
    RecyclerView spareRecyclerView;
    EOBooking eoBooking;
    LinearLayout sparerequstedbysfLayout, estimateGivenLayout, sparePartShippedLayout,
            defectiveSapreShippedbysfLayout, InvoiceIDLayout, OUWInvoiceDetailLayout, spareReqLayout, spareShippedLayout, defectiveSpareLayout,
            oowInvoiceLayout, invoiceLayout,estimateLayout;
    boolean isResponseCome;
    EOSparePartTabResponse eoSparePartTabResponse;
    LayoutInflater inflater;
    ImageView expandIcon, estimateExpandIcon, spareshippedexpandIcon, defectiveSparedexpandIcon, invoiceExpandIcon, oowInvoiceexpandIcon;
    TextView shippednodata,estimatenodata,noDataToDisplay,defectivenodata;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.eoBooking = getArguments().getParcelable("eoBooking");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.sparepart_tab_fragment, container, false);
        this.inflater = inflater;
        this.sparerequstedbysfLayout = this.view.findViewById(R.id.sparerequstedbysfLayout);
        this.estimateGivenLayout = this.view.findViewById(R.id.estimateGivenLayout);
        this.estimateLayout = this.view.findViewById(R.id.estimateLayout);
        this.spareShippedLayout = this.view.findViewById(R.id.spareShippedLayout);
        this.defectiveSpareLayout = this.view.findViewById(R.id.defectiveSpareLayout);
        this.invoiceLayout = this.view.findViewById(R.id.invoiceLayout);
        this.oowInvoiceLayout = this.view.findViewById(R.id.oowInvoiceLayout);
        this.sparePartShippedLayout = this.view.findViewById(R.id.sparePartShippedLayout);
        this.defectiveSapreShippedbysfLayout = this.view.findViewById(R.id.defectiveSapreShippedbysfLayout);
        this.InvoiceIDLayout = this.view.findViewById(R.id.InvoiceIDLayout);
        this.OUWInvoiceDetailLayout = this.view.findViewById(R.id.OUWInvoiceDetailLayout);
        this.spareReqLayout = this.view.findViewById(R.id.spareReqLayout);
        this.expandIcon = this.view.findViewById(R.id.expandIcon);
        this.estimateExpandIcon = this.view.findViewById(R.id.estimateExpandIcon);
        this.spareshippedexpandIcon = this.view.findViewById(R.id.spareshippedexpandIcon);
        this.defectiveSparedexpandIcon = this.view.findViewById(R.id.defectiveSparedexpandIcon);
        this.invoiceExpandIcon = this.view.findViewById(R.id.invoiceExpandIcon);
        this.oowInvoiceexpandIcon = this.view.findViewById(R.id.oowInvoiceexpandIcon);
        shippednodata=this.view.findViewById(R.id.shippednodata);
        estimatenodata=this.view.findViewById(R.id.estimatenodata);
        this.noDataToDisplay=this.view.findViewById(R.id.noDataToDisplay);
        this.defectivenodata=this.view.findViewById(R.id.defectivenodata);



        this.expandIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spareReqLayout.getVisibility() == View.VISIBLE) {
                    spareReqLayout.setVisibility(View.GONE);
                } else {
                    showSpareInfo();
                    spareReqLayout.setVisibility(View.VISIBLE);
                    estimateGivenLayout.setVisibility(View.GONE);
                    spareShippedLayout.setVisibility(View.GONE);
                    defectiveSpareLayout.setVisibility(View.GONE);
                    invoiceLayout.setVisibility(View.GONE);
                    oowInvoiceLayout.setVisibility(View.GONE);


                }
            }
        });

        this.estimateExpandIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estimateGivenLayout.getVisibility() == View.VISIBLE) {
                    estimateGivenLayout.setVisibility(View.GONE);
                } else {
                    showEstimateGiven();

                    estimateGivenLayout.setVisibility(View.VISIBLE);
                    spareReqLayout.setVisibility(View.GONE);
                    spareShippedLayout.setVisibility(View.GONE);
                    defectiveSpareLayout.setVisibility(View.GONE);
                    invoiceLayout.setVisibility(View.GONE);
                    oowInvoiceLayout.setVisibility(View.GONE);
                }
            }
        });
        this.spareshippedexpandIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spareShippedLayout.getVisibility() == View.VISIBLE) {
                    spareShippedLayout.setVisibility(View.GONE);

                } else {
                    showSpareShipped();
                    spareShippedLayout.setVisibility(View.VISIBLE);
                    spareReqLayout.setVisibility(View.GONE);
                    estimateGivenLayout.setVisibility(View.GONE);
                    defectiveSpareLayout.setVisibility(View.GONE);
                    invoiceLayout.setVisibility(View.GONE);
                    oowInvoiceLayout.setVisibility(View.GONE);
                }
            }
        });
        this.defectiveSparedexpandIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (defectiveSpareLayout.getVisibility() == View.VISIBLE) {
                    defectiveSpareLayout.setVisibility(View.GONE);

                } else {
                    showDefectiveSpareShipped();
                    defectiveSpareLayout.setVisibility(View.VISIBLE);
                    spareReqLayout.setVisibility(View.GONE);
                    estimateGivenLayout.setVisibility(View.GONE);
                    spareShippedLayout.setVisibility(View.GONE);
                    invoiceLayout.setVisibility(View.GONE);
                    oowInvoiceLayout.setVisibility(View.GONE);
                }
            }
        });
        this.invoiceExpandIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (invoiceLayout.getVisibility() == View.VISIBLE) {
                    invoiceLayout.setVisibility(View.GONE);

                } else {
                 //   showInvoice();
                    invoiceLayout.setVisibility(View.VISIBLE);
                    spareReqLayout.setVisibility(View.GONE);
                    estimateGivenLayout.setVisibility(View.GONE);
                    spareShippedLayout.setVisibility(View.GONE);
                    defectiveSpareLayout.setVisibility(View.GONE);
                    oowInvoiceLayout.setVisibility(View.GONE);
                }
            }
        });
        this.oowInvoiceexpandIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oowInvoiceLayout.getVisibility() == View.VISIBLE) {
                    oowInvoiceLayout.setVisibility(View.GONE);
                } else {
                  //  showOOWInvoice();
                    oowInvoiceLayout.setVisibility(View.VISIBLE);
                    spareReqLayout.setVisibility(View.GONE);
                    estimateGivenLayout.setVisibility(View.GONE);
                    spareShippedLayout.setVisibility(View.GONE);
                    defectiveSpareLayout.setVisibility(View.GONE);
                    invoiceLayout.setVisibility(View.GONE);
                }
            }
        });
//        this.sparerequstedbysfLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString(BMAConstants.HEADER_TXT, "Spare Requested ");
//                SpareRequestedBySF spareRequestedBySF = new SpareRequestedBySF();
//                spareRequestedBySF.setArguments(bundle);
//                // getMainActivity().updateFragment(spareRequestedBySF, true);
//            }
//        });
//        this.estimateGivenLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString(BMAConstants.HEADER_TXT, "Estimate Given ");
//                SpareRequestedBySF spareRequestedBySF = new SpareRequestedBySF();
//                spareRequestedBySF.setArguments(bundle);
//                getMainActivity().updateFragment(spareRequestedBySF, true);
//            }
//        });
//        this.sparePartShippedLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString(BMAConstants.HEADER_TXT, "Part Shipped");
//                SparePartShippedFragment sparePartShippedFragment = new SparePartShippedFragment();
//                sparePartShippedFragment.setArguments(bundle);
//                getMainActivity().updateFragment(sparePartShippedFragment, true);
//            }
//        });
//        this.defectiveSapreShippedbysfLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString(BMAConstants.HEADER_TXT, "Defective Part Shipped ");
//                DefectiveSparePartShippedFragment defectiveSparePartShippedFragment = new DefectiveSparePartShippedFragment();
//                defectiveSparePartShippedFragment.setArguments(bundle);
//                getMainActivity().updateFragment(defectiveSparePartShippedFragment, true);
//            }
//        });
//        this.InvoiceIDLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString(BMAConstants.HEADER_TXT, "Invoice Detials");
//                SpareRequestedBySF spareRequestedBySF = new SpareRequestedBySF();
//                spareRequestedBySF.setArguments(bundle);
//                getMainActivity().updateFragment(spareRequestedBySF, true);
//            }
//        });
//        this.OUWInvoiceDetailLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString(BMAConstants.HEADER_TXT, "OUW Invoice");
//                SpareRequestedBySF spareRequestedBySF = new SpareRequestedBySF();
//                spareRequestedBySF.setArguments(bundle);
//                getMainActivity().updateFragment(spareRequestedBySF, true);
//            }
//        });
        //  this.spareRecyclerView = this.view.findViewById(R.id.spareRecyclerView);
        // this.dataToView();
        if (isVisibleToUser && !isResponseCome) {
            getRequest();
        }
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

    boolean isVisibleToUser;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser && this.eoBooking != null) {
            getRequest();
        }
    }

//    public FragmentTransaction setMaxLifecycle(Fragment fragment, Lifecycle.State RESUMED){
//
//    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("aaaa", "onResume .. ");
    }

    HttpRequest httpRequest;

    private void getRequest() {
        httpRequest = new HttpRequest(getMainActivity(), true);
        httpRequest.delegate = SparePartStatus.this;
//        SharedPreferences sharePrefrence= MainActivityHelper.applicationHelper().getSharedPrefrences(BMAConstants.LOGIN_INFO);
//        String entityID=sharePrefrence.getString("entityID", null);
//        String entityType=sharePrefrence.getString("entityType", null);
//        this.actionID = "searchData";
        //  Log.d("aaaaaaa"," All Task engineerID = "+MainActivityHelper.applicationHelper().getSharedPrefrences().getString("engineerID","abcfegd")+"    service id = "+MainActivityHelper.applicationHelper().getSharedPrefrences().getString("service_center_id", null));
        httpRequest.execute("getSpareTabData", this.eoBooking.bookingID);//,batteryLevel+"");

    }

    @Override
    public void processFinish(String response) {
        super.processFinish(response);
        isResponseCome = true;
        Log.d("bbbbbbb", "response  = " + response);


        httpRequest.progress.dismiss();
        if (response.contains("data")) {
            JSONObject jsonObjectHttpReq;

            try {
                jsonObjectHttpReq = new JSONObject(response);

                final JSONObject jsonObject = jsonObjectHttpReq.getJSONObject("data");
                String statusCode = jsonObject.getString("code");
                if (statusCode.equals("0000")) {
                    JSONObject responseData = new JSONObject(jsonObject.getString("response"));

                    this.eoSparePartTabResponse = BMAGson.store().getObject(EOSparePartTabResponse.class, jsonObject.get("response"));
                    if(this.eoSparePartTabResponse!=null ) {
                        if(!checkNotEmptyList(this.eoSparePartTabResponse.estimate_given) && !checkNotEmptyList(this.eoSparePartTabResponse.spare_defective)
                        && !checkNotEmptyList(this.eoSparePartTabResponse.spare_shipped) && !checkNotEmptyList(this.eoSparePartTabResponse.spare_requested)){
                            noDataToDisplay.setVisibility(View.VISIBLE);
                        }

                        bindDataToView();
                    }else{
                        noDataToDisplay.setVisibility(View.VISIBLE);
                    }
//                    ArrayList<EOBooking> searchedBookingList = BMAGson.store().getList(EOBooking.class, jsonObject.getJSONObject("response").getString("Bookings"));
//                    openSearchedBookingPage(searchedBookingList);
//
//
//                } else if (this.actionID.equalsIgnoreCase("searchData")) {
//                    //  if(statusCode.equals("0061")){
//                    BMAAlertDialog bmaAlertDialog = new BMAAlertDialog(getContext(), false, true) {
//
//
//                        @Override
//                        public void onWarningDismiss() {
//                            super.onWarningDismiss();
//                        }
//                    };
//                    String result = jsonObject.getString("result");
//                    if (result == null || result.length() == 0) {
//                        result = "Data not found";
//                    }
//                    bmaAlertDialog.show(result);
//                    //   }
                }
            } catch (Exception e) {

            }
//        }else{
//            BMAAlertDialog bmaAlertDialog = new BMAAlertDialog(getContext(), false, true) {
//
//
//                @Override
//                public void onWarningDismiss() {
//                    super.onWarningDismiss();
//                }
//            };
//
//            bmaAlertDialog.show("Server Error");
        }else{
            this.noDataToDisplay.setVisibility(View.VISIBLE);
        }
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


    }

    private void bindDataToView() {
        if(!checkNotEmptyList(this.eoSparePartTabResponse.estimate_given)){
            estimateLayout.setVisibility(View.GONE);
        }
        if(!checkNotEmptyList(this.eoSparePartTabResponse.spare_defective)){
            defectiveSapreShippedbysfLayout.setVisibility(View.GONE);
        }
        if(!checkNotEmptyList(this.eoSparePartTabResponse.spare_shipped)){
            sparePartShippedLayout.setVisibility(View.GONE);
        }
        if(!checkNotEmptyList(this.eoSparePartTabResponse.spare_requested)){
            spareReqLayout.setVisibility(View.GONE);
        }
        showSpareInfo();
    }
    private boolean checkNotEmptyList(ArrayList<?> list){
        return list!=null && list.size()>0 ;

    }

    private void showSpareInfo() {
        if(this.eoSparePartTabResponse.spare_requested.size()>0) {
            this.spareReqLayout.removeAllViews();
        }
        for (final EOSpare eoSpare : this.eoSparePartTabResponse.spare_requested) {
            View itemView = inflater.inflate(R.layout.spare_item_card, null, false);
            TextView partName = itemView.findViewById(R.id.partName);
            TextView partNumber = itemView.findViewById(R.id.partNumber);
            TextView partType = itemView.findViewById(R.id.partType);
            TextView quantity = itemView.findViewById(R.id.quantity);
            TextView status = itemView.findViewById(R.id.status);
            partName.setText(eoSpare.parts_requested);
            partNumber.setText(eoSpare.part_number);
            partType.setText(eoSpare.parts_requested_type);
            status.setText(eoSpare.part_warranty_status);
            quantity.setText(eoSpare.quantity);
            LinearLayout rowLayout = itemView.findViewById(R.id.rowLayout);
            rowLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString(BMAConstants.HEADER_TXT, "Spare Requested ");
                    bundle.putParcelable("spareRequested", eoSpare);
                    SpareRequestedBySF spareRequestedBySF = new SpareRequestedBySF();
                    spareRequestedBySF.setArguments(bundle);
                    getMainActivity().updateFragment(spareRequestedBySF, true);
                }
            });

            this.spareReqLayout.addView(itemView);
        }

    }

    private void showEstimateGiven() {

        if(this.eoSparePartTabResponse!=null && eoSparePartTabResponse.spare_oow.size()>0){
            estimatenodata.setVisibility(View.GONE);
            this.estimateGivenLayout.removeAllViews();
        }
        for (EOSpare eoSpare : this.eoSparePartTabResponse.spare_oow) {
            View itemView = inflater.inflate(R.layout.spare_item_card, null, false);

            TextView partNameTextView = itemView.findViewById(R.id.partNameTextView);
            partNameTextView.setText("Estimate Given By");

            TextView partName = itemView.findViewById(R.id.partName);
            TextView partNumberTextView = itemView.findViewById(R.id.partNumberTextView);
            partNumberTextView.setText("Estimate Cost");
            TextView partNumber = itemView.findViewById(R.id.partNumber);

            TextView partTypeTextView = itemView.findViewById(R.id.partTypeTextView);
            partTypeTextView.setText("Estimate Given Date");
            TextView partType = itemView.findViewById(R.id.partType);

            TextView quantityTetxview = itemView.findViewById(R.id.quantityTetxview);
            quantityTetxview.setText("Sale Invoice ID");
            TextView quantity = itemView.findViewById(R.id.quantity);
            TextView status = itemView.findViewById(R.id.status);
            partName.setText(eoSpare.entity_type);
            partNumber.setText(eoSpare.purchase_price+"  Rs");
            partType.setText(eoSpare.estimate_cost_given_date);
            status.setText(eoSpare.status);

            quantity.setText(eoSpare.sell_invoice_id!=null ? eoSpare.sell_invoice_id:"Not Available");
            LinearLayout rowLayout = itemView.findViewById(R.id.rowLayout);

            this.estimateGivenLayout.addView(itemView);

        }
    }
    private void showSpareShipped() {

        if(this.eoSparePartTabResponse.spare_shipped.size()>0) {
            shippednodata.setVisibility(View.GONE);
            spareShippedLayout.removeAllViews();
        }
        for (final EOSpare eoSpare : this.eoSparePartTabResponse.spare_shipped) {
            View itemView = inflater.inflate(R.layout.spare_item_card, null, false);
            TextView partName = itemView.findViewById(R.id.partName);
            TextView partNumber = itemView.findViewById(R.id.partNumber);
            TextView partType = itemView.findViewById(R.id.partType);
            TextView quantity = itemView.findViewById(R.id.quantity);
            TextView status = itemView.findViewById(R.id.status);
            partName.setText(eoSpare.parts_shipped);
            partNumber.setText(eoSpare.shipped_part_number!=null ? eoSpare.shipped_part_number : "Not Available");
            partType.setText(eoSpare.shipped_parts_type);
            status.setText("Not Available");
            quantity.setText(eoSpare.shipped_quantity);
            LinearLayout rowLayout = itemView.findViewById(R.id.rowLayout);
            rowLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString(BMAConstants.HEADER_TXT, "Spare Shipped ");
                    bundle.putParcelable("spareShipped",eoSpare);
                    SparePartShippedFragment sparePartShippedFragment = new SparePartShippedFragment();
                    sparePartShippedFragment.setArguments(bundle);
                    getMainActivity().updateFragment(sparePartShippedFragment, true);
                }
            });
            this.spareShippedLayout.addView(itemView);

        }
    }

//    private void showOOWInvoice() {
//        for (EOSpare eoSpare : this.eoSparePartTabResponse.spare_oow) {
//            View itemView = inflater.inflate(R.layout.spare_item_card, null, false);
//            TextView partName = itemView.findViewById(R.id.partName);
//            TextView partNumber = itemView.findViewById(R.id.partNumber);
//            TextView partType = itemView.findViewById(R.id.partType);
//            TextView quantity = itemView.findViewById(R.id.quantity);
//            TextView status = itemView.findViewById(R.id.status);
//            partName.setText(eoSpare.parts_requested);
//            partNumber.setText(eoSpare.part_number);
//            partType.setText(eoSpare.parts_requested_type);
//            status.setText(eoSpare.part_warranty_status);
//            quantity.setText(eoSpare.quantity);
//
//
//            this.spareReqLayout.addView(itemView);
//        }
//    }

    private void showDefectiveSpareShipped() {
        if(this.eoSparePartTabResponse.spare_defective.size()>0) {
            defectivenodata.setVisibility(View.GONE);
            defectiveSpareLayout.removeAllViews();
        }
        for (final EOSpare eoSpare : this.eoSparePartTabResponse.spare_defective) {
            View itemView = inflater.inflate(R.layout.spare_item_card, null, false);
            TextView partName = itemView.findViewById(R.id.partName);
            TextView partNumber = itemView.findViewById(R.id.partNumber);
            TextView partType = itemView.findViewById(R.id.partType);
            TextView quantity = itemView.findViewById(R.id.quantity);
            TextView status = itemView.findViewById(R.id.status);
            partName.setText(eoSpare.defective_part_shipped);
            partNumber.setText(eoSpare.shipped_part_number!=null ? eoSpare.shipped_part_number : "Not Available");
            partType.setText(eoSpare.shipped_parts_type!=null ? eoSpare.shipped_parts_type : "Not Available");
            status.setText("Not Available");
            quantity.setText(eoSpare.shipped_quantity);
            LinearLayout rowLayout = itemView.findViewById(R.id.rowLayout);
            rowLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString(BMAConstants.HEADER_TXT, "Spare Defective ");
                    bundle.putParcelable("spareShipped",eoSpare);
                    DefectiveSparePartShippedFragment sparePartShippedFragment = new DefectiveSparePartShippedFragment();
                    sparePartShippedFragment.setArguments(bundle);
                    getMainActivity().updateFragment(sparePartShippedFragment, true);
                }
            });


            this.defectiveSpareLayout.addView(itemView);
        }
    }

//    private void showInvoice() {
//        for (EOSpare eoSpare : this.eoSparePartTabResponse.spare_invoice) {
//            View itemView = inflater.inflate(R.layout.spare_item_card, null, false);
//            TextView partName = itemView.findViewById(R.id.partName);
//            TextView partNumber = itemView.findViewById(R.id.partNumber);
//            TextView partType = itemView.findViewById(R.id.partType);
//            TextView quantity = itemView.findViewById(R.id.quantity);
//            TextView status = itemView.findViewById(R.id.status);
//            partName.setText(eoSpare.parts_requested);
//            partNumber.setText(eoSpare.part_number);
//            partType.setText(eoSpare.parts_requested_type);
//            status.setText(eoSpare.part_warranty_status);
//            quantity.setText(eoSpare.quantity);
//
//
//            this.spareReqLayout.addView(itemView);
//        }
//
//    }
}
