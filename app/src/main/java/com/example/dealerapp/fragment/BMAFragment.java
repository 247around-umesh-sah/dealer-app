package com.example.dealerapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dealerapp.MainActivity;
import com.example.dealerapp.adapter.BMARecyclerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import helper.ApiResponse;
import helper.MainActivityHelper;

// this fragment is used to extend all fragment class
public class BMAFragment extends Fragment implements ApiResponse, BMARecyclerAdapter.BMAListRowCreator {
    View view;
    private LocationManager mLocationManager;
   // Location currentLocation;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       // mLocationManager = (LocationManager) getMainActivity().getSystemService(Context.LOCATION_SERVICE);
        //  this.hideSoftKeyboard(this.getActivity());
       // savePageNameForAmplitude();

    }

    public void savePageNameForAmplitude(){
      //  BMAmplitude.saveUserAction(getPageName(),getPageName());
    }
    public String getPageName(){
        return getMainActivity().getPageFragment().getClass().getSimpleName();
    }

    // this method called automatically when server response come.
    @Override
    public void processFinish(String response) {

    }

    public void loadRecyclerViewAdapter(RecyclerView recyclerView, Context context, BMARecyclerAdapter.BMAListRowCreator bmaListRowCreator, int layoutID) {
//        Context context,List list,RecyclerView recyclerView, BMARecyclerAdapter.BMAListRowCreator
//        bmaListRowCreator,int layoutID
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("aaa");
        stringList.add("bbbbb");
        // recyclerView.setAdapter(new BMARecyclerAdapter(context,stringList,recyclerView,bmaListRowCreator,layoutID));

    }

    public MainActivity getMainActivity() {
        FragmentActivity fragmentActivity = getActivity();
        return fragmentActivity != null ? (MainActivity) fragmentActivity : MainActivityHelper.application();
    }

    @Override
    public <T> void createRow(View convertView, T rowObject) {

    }

    @Override
    public <T> void createRow(RecyclerView.ViewHolder viewHolder, View itemView, T rowObject, int position) {

    }

    @Override
    public <T> void createHeader(View convertView, T rowObject) {

    }

    public void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            hideKeyBoard(activity, activity.getCurrentFocus());
        } else {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }
    }

    public void hideKeyBoard(Context context, View v) {
        if (v == null) {
            return;
        }
        getMainActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public Location getLocation(String address) {
        Location location = null;
        address.replaceAll(","," ");
//        Log.d("aaaaa","adress = "+address);
        //String address = "c 68 rama 1 greater noida";// 201310;
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocationName(address, 5);
//            Log.d("aaaaa","adressList= "+addressList);
            //Log.d("aaaaaa", "latitude..... = " + addressList.get(0).getLatitude() + "      longitude..#### = " + addressList.get(0).getLongitude());
            if (addressList != null && addressList.size() > 0) {
                Address addressLoc = addressList.get(0);
//                Log.d("aaaaa","addressLoc= "+addressLoc);
                location = new Location(LocationManager.GPS_PROVIDER);
                location.setLatitude(addressLoc.getLatitude());
                location.setLongitude(addressLoc.getLongitude());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return location;
    }





    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.stopUpdateDistance();


    }
    Handler timerHandler;
    Runnable timerRunnable;


    private void stopUpdateDistance(){
        if (this.timerHandler != null) {
            this.timerHandler.removeCallbacks(this.timerRunnable);
            timerHandler = null;
        }
    }
    public void noitifyDataSetChangedAdapter(){

    }


    public void startSearch(String filterStr) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("aaaaa","bmafragment");
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        Log.d("aaaaa","bmafragment ONREQUEST PERMISSION");
//    }
}

