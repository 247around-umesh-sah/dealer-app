package com.example.dealerapp.adapter;



import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.dealerapp.entity.EOBooking;
import com.example.dealerapp.fragment.ApplianceDetails;
import com.example.dealerapp.fragment.BMAFragment;
import com.example.dealerapp.fragment.BookingDetails;
import com.example.dealerapp.fragment.DashBoardFragment;
import com.example.dealerapp.fragment.SparePartStatus;

import java.util.ArrayList;
import java.util.List;

public class CustomFragmentPageAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = CustomFragmentPageAdapter.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 3;
    List<Fragment> fragmentList = new ArrayList<>();
    EOBooking eoBooking;


    public CustomFragmentPageAdapter(FragmentManager fm,EOBooking eoBooking) {
        super(fm);
        this.eoBooking=eoBooking;
    }

    @Override
    public BMAFragment getItem(int position) {
        Bundle bundle=new Bundle();
        bundle.putParcelable("eoBooking",eoBooking);
        switch (position) {
            case 0:
                BookingDetails bookingDetails=new BookingDetails();
                bookingDetails.setArguments(bundle);

                return bookingDetails;
            case 1:
                ApplianceDetails applianceDetails=new ApplianceDetails();
                applianceDetails.setArguments(bundle);
                return applianceDetails;
            case 2:
                SparePartStatus sparePartStatus=new SparePartStatus();
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
