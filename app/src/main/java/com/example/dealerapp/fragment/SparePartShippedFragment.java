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

public class SparePartShippedFragment extends BMAFragment {
    ArrayList<String>sparePartShippedList=new ArrayList<>();
    LinearLayout mainLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.sparetablayout, container, false);
        this.mainLayout=this.view.findViewById(R.id.mainLayout);
        bindData(inflater);
        return this.view;
    }
    private void bindData(@NonNull LayoutInflater inflater){


        for(int i=0;i<sparePartShippedList.size();i++){
            View childView = inflater.inflate(R.layout.spare_requested, null, false);
            bindData(childView);
            this.mainLayout.addView(childView);
        }


    }

    private void bindData(View itemView){

        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName=itemView.findViewById(R.id.brandName);


    }
}
