package com.example.dealerapp.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dealerapp.R;
import com.example.dealerapp.adapter.BMARecyclerAdapter;
import com.example.dealerapp.component.BMAAlertDialog;
import com.example.dealerapp.entity.BookingInfo;
import com.example.dealerapp.entity.EOBooking;

import java.util.ArrayList;

import util.BMAConstants;
import util.BMAUtil;

// this class uses to show searched booking list
public class SearchedBookingFragment extends BMAFragment {


    TextView tomorrowText, bookingText, countBooking;
    ImageView bookingTypeImage;
    View devider;
    LinearLayout bookingHeaderLayout, noDataToDisplayLayout;
    RecyclerView recyclerView;
    boolean isMissed;
    // ProgressBar progressBar;
    BookingInfo bookingInfo;
    BMARecyclerAdapter bmaRecyclerAdapter;
    ArrayList<EOBooking> searchedBookingList = new ArrayList<>();
    String filterStr;
    //////pk.eyJ1IjoidWtzYWgiLCJhIjoiY2p2cW5iaHZnMHlpYjQ0cWw4M2Y3eDRwcyJ9.MPePwKBaJVKg4BTHhYgMFA


    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onAttach(Context context) {

        this.searchedBookingList = this.getArguments().getParcelableArrayList("searchedBookingList");
        this.filterStr = getArguments().getString("filterStr");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.searche_fragment, container, false);

        this.recyclerView = this.view.findViewById(R.id.recyclerView);
        this.countBooking = this.view.findViewById(R.id.count_booking);
        this.tomorrowText = this.view.findViewById(R.id.tomorrowText);
        this.bookingText = this.view.findViewById(R.id.bookingText);
        this.bookingTypeImage = this.view.findViewById(R.id.bookingTypeImage);
        this.devider = this.view.findViewById(R.id.deviderView);
        this.bookingHeaderLayout = this.view.findViewById(R.id.headerLayout);
        this.noDataToDisplayLayout = this.view.findViewById(R.id.nodataToDisplayLayout);

        BMARecyclerAdapter bmaRecyclerAdapter = new BMARecyclerAdapter(getContext(), this.getBookingList(), recyclerView, this, R.layout.searched_item_row);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(bmaRecyclerAdapter);

        this.dataToView();


        return this.view;
    }

    // HttpRequest httpRequest;


    @Override
    public <T> void createRow(RecyclerView.ViewHolder viewHolder, View itemView, T rowObject, int position) {
        RelativeLayout rowLayout = itemView.findViewById(R.id.rowLayout);
        LinearLayout dateLayout = itemView.findViewById(R.id.dateLayout);

       // BMAmplitude.saveUserAction("SearchedBooking", "SearchedBooking");
        final EOBooking eoBooking = (EOBooking) rowObject;
       // dateLayout.setBackgroundColor(eoBooking.internal_status.equalsIgnoreCase(BMAConstants.INTERNAL_SATATUS_CANCELLED) || eoBooking.current_status.equalsIgnoreCase("Cancelled") ? BMAUIUtil.getColor(R.color.blue_black) : BMAUIUtil.getColor(R.color.missedBookingcolor));
        TextView name = itemView.findViewById(R.id.name);
        TextView address = itemView.findViewById(R.id.address);
        TextView brandName = itemView.findViewById(R.id.brandName);
        TextView bookingID = itemView.findViewById(R.id.chargableservice);
        bookingID.setText(eoBooking.bookingID);
        TextView serviceName = itemView.findViewById(R.id.serviceName);
        TextView date = itemView.findViewById(R.id.date);
        TextView distance = itemView.findViewById(R.id.distance);
        TextView chargeableAmount = itemView.findViewById(R.id.chargeableAmount);
        name.setText(eoBooking.name);
        address.setText(eoBooking.bookingAddress);
        brandName.setText(eoBooking.applianceBrand);
        serviceName.setText(eoBooking.services + "-" + eoBooking.requestType);
        date.setText(eoBooking.bookingDate);
        distance.setText(eoBooking.bookingDistance);
        distance.setVisibility(View.GONE);
        chargeableAmount.setText("" + eoBooking.dueAmount);
        ImageView phone = itemView.findViewById(R.id.phone);
       // ImageView mapIcon = itemView.findViewById(R.id.mapLocArrow);
      //  mapIcon.setVisibility(View.INVISIBLE);
      //  mapIcon.setTag(eoBooking);
//        mapIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isEditBooking((EOBooking) v.getTag())) {
//                    Fragment fragment = new BMAMapFragment();
//                    Bundle bundl = new Bundle();
//                    bundl.putParcelable("eoBooking", eoBooking);
//                    bundl.putString(BMAConstants.HEADER_TXT, "");
//                    fragment.setArguments(bundl);
//                    getMainActivity().updateFragment(fragment, true);
//                }
//            }
//        });
        phone.setTag(eoBooking);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   if (isEditBooking((EOBooking) v.getTag())) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                        BMAAlertDialog dialog = new BMAAlertDialog(getActivity(), true, false) {
                            @Override
                            public void onConfirmation() {
                                this.dismiss();
                                doCall(eoBooking.primaryContact);
                            }
                        };
                        dialog.show(getString(R.string.enableCallFeatureValiadtion));
                    } else {

                        doCall(eoBooking.primaryContact);
                    }

                }
           // }
        });

        rowLayout.setTag(eoBooking);
        rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  if (isEditBooking((EOBooking) v.getTag())) {
                    Fragment fragment = new FragmentLoader();
                    Bundle bundl = new Bundle();
                    bundl.putParcelable("eoBooking", eoBooking);
                 //   bundl.putString(BMAConstants.HEADER_TXT, "");
                    fragment.setArguments(bundl);

                    getMainActivity().updateFragment(fragment, true, null);
               // }
            }
        });

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    // In this method we bind the adapter to recylver aand item row layout
    private void dataToView() {
        //    this.countBooking.setText("" + (this.getBookingList().size() < 10 ? "0" + this.getBookingList().size() : this.getBookingList().size()));
        this.bmaRecyclerAdapter = new BMARecyclerAdapter(getContext(), this.getBookingArray(), recyclerView, this, R.layout.searched_item_row);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.recyclerView.setAdapter(bmaRecyclerAdapter);
//        if(isSetDistance) {
//            this.updateDistance();
//        }
        //   this.httpRequest.progress.dismiss();

    }


    public void noitifyDataSetChangedAdapter() {
        //  httpRequest.progress.dismiss();
        this.bmaRecyclerAdapter.notifyDataSetChanged();
    }


    public ArrayList<EOBooking> getBookingList() {
        return this.searchedBookingList != null ? searchedBookingList : new ArrayList<EOBooking>();

    }

    // In this method we initalizze name after formated name and returnn booking list
    private ArrayList<EOBooking> getBookingArray() {
        for (EOBooking eoBooking : getBookingList()) {
            eoBooking.name = BMAUtil.getTitleString(eoBooking.name);
        }
        return getBookingList();
    }


    // This method is used to call when dealer press call icon
    private void doCall(final String mobNumber) {

        //By Nitin Sir
        BMAAlertDialog bmaAlertDialog = new BMAAlertDialog(getContext(), true, false) {
            @Override
            public void onConfirmation() {
                super.onConfirmation();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobNumber));
                startActivity(intent);
            }
        };
        bmaAlertDialog.show(getString(R.string.callConfirmationMessage));

    }

    int position;





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }

        if (data.getBooleanExtra("isCancelled", false)) {
          //  if (this.getBookingList().size() == 1) {

                getTargetFragment().onActivityResult(getTargetRequestCode(), BMAConstants.requestCode, new Intent());
                getFragmentManager().popBackStack();
//            } else {
//                getSearchedData();
//            }
        }
    }




}
