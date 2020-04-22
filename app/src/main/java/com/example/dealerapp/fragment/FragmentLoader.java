package com.example.dealerapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.dealerapp.R;
import com.example.dealerapp.adapter.CustomFragmentPageAdapter;
import com.example.dealerapp.entity.EOBooking;
import com.google.android.material.tabs.TabLayout;

import util.BMAUIUtil;


public class FragmentLoader extends BMAFragment {

    private static final String TAG = FragmentLoader.class.getSimpleName();

    private TabLayout tabLayout;
    private ViewPager viewPager;
public CustomFragmentPageAdapter customFragmentPageAdapter;
public int index=0;
EOBooking eoBooking;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.eoBooking=getArguments().getParcelable("eoBooking");
    }

    public FragmentLoader() {

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loader, container, false);

        tabLayout = view.findViewById(R.id.tabs);
        viewPager = view.findViewById(R.id.view_pager);

        this.customFragmentPageAdapter=new CustomFragmentPageAdapter(getChildFragmentManager(),eoBooking);
        viewPager.setAdapter(this.customFragmentPageAdapter);
        viewPager.setCurrentItem(index);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0);
        if(this.customFragmentPageAdapter.getCount()>1){
            this.tabLayout.setBackgroundColor(BMAUIUtil.getColor(R.color.colorPrimary));
           // this.tabLayout.tabind
        }

        return view;
    }
    public void loadTab(int index){
        viewPager.setCurrentItem(index);
    }
    public Fragment getCurrentPage(){
        return this.customFragmentPageAdapter.getItem(this.viewPager.getCurrentItem());
        //for (int i = 0; i < tabLayout.getTabCount(); i++) {

        //}
    }

    @Override
    public void startSearch(String filterStr) {
        if (getCurrentFragment() != null) {
            getCurrentFragment().startSearch(filterStr);
        }
    }
    public BMAFragment getCurrentFragment() {
        return this.getFragmentAtIndex(this.viewPager != null ? this.viewPager.getCurrentItem() : 0);
    }

    public BMAFragment getFragmentAtIndex(int index) {
        return this.customFragmentPageAdapter != null ? this.customFragmentPageAdapter.getItem(index) : null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.getCurrentFragment().onActivityResult(requestCode, resultCode, data);
       Log.d("aaaaaa","onActitivity FragmentLoader = "+data);
    }
}
