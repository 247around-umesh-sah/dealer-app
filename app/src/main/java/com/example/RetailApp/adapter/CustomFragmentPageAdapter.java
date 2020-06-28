package com.example.RetailApp.adapter;



import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.RetailApp.MainActivity;
import com.example.RetailApp.entity.EOBooking;
import com.example.RetailApp.fragment.ApplianceDetails;
import com.example.RetailApp.fragment.BMAFragment;
import com.example.RetailApp.fragment.BookingDetails;
import com.example.RetailApp.fragment.SparePartStatus;

import java.util.ArrayList;
import java.util.List;

import util.BMAConstants;

public class CustomFragmentPageAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = CustomFragmentPageAdapter.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 3;
    List<Fragment> fragmentList = new ArrayList<>();
    EOBooking eoBooking;
    MainActivity activity;


    public CustomFragmentPageAdapter(FragmentManager fm,EOBooking eoBooking,MainActivity activity) {
        super(fm);
        this.eoBooking=eoBooking;
        this.activity=activity;
    }

    @Override
    public BMAFragment getItem(int position) {
        Log.d("aaaaaa","get Item .. ");
        Bundle bundle=new Bundle();
        bundle.putParcelable("eoBooking",eoBooking);
        switch (position) {
            case 0:
                BookingDetails bookingDetails=new BookingDetails();
               // activity.getHeaderFragment().headerTxt=eoBooking.bookingID;
                bundle.putString(BMAConstants.HEADER_TXT, eoBooking.bookingID);
                bookingDetails.setArguments(bundle);

                return bookingDetails;
            case 1:
                ApplianceDetails applianceDetails=new ApplianceDetails();
               // activity.getHeaderFragment().headerTxt="Appliance Detail";
                bundle.putString(BMAConstants.HEADER_TXT, "Appliance Detail");
                applianceDetails.setArguments(bundle);
                return applianceDetails;
            case 2:
                SparePartStatus sparePartStatus=new SparePartStatus();
                //activity.getHeaderFragment().headerTxt="Spare Parts";
                bundle.putString(BMAConstants.HEADER_TXT, "Spare Parts");
                sparePartStatus.setArguments(bundle);
                return sparePartStatus;
            case 3:
                //return new AllTasksFragment();


        }
        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Log.d("aaaaa","getPage Title = "+position);
        switch (position) {
            case 0:
                return "Booking Details";
            case 1:

                return "Appliance Details";
            case 2:

                return "Spare Part Status";

        }
        return null;
    }
}
