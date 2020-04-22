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

public class DefectiveSparePartShippedFragment extends BMAFragment {

    ArrayList<String> defectiveSpareList=new ArrayList<>();
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
        View childView = inflater.inflate(R.layout.spare_requested, null, false);

        for(int i=0;i<defectiveSpareList.size();i++){
            bindData(childView);
            this.mainLayout.addView(childView);
        }


    }

    private void bindData(View itemView){

        TextView brandName=itemView.findViewById(R.id.brandName);
        TextView brandName1=itemView.findViewById(R.id.brandName);
        TextView brandName2=itemView.findViewById(R.id.brandName);
        TextView brandName3=itemView.findViewById(R.id.brandName);
        TextView brandName4=itemView.findViewById(R.id.brandName);
        TextView brandName5=itemView.findViewById(R.id.brandName);
        TextView brandName6=itemView.findViewById(R.id.brandName);
        TextView brandName7=itemView.findViewById(R.id.brandName);
        TextView brandName8=itemView.findViewById(R.id.brandName);
        TextView brandName9=itemView.findViewById(R.id.brandName);
        TextView brandNamea=itemView.findViewById(R.id.brandName);
        TextView brandNameb=itemView.findViewById(R.id.brandName);
        TextView brandNamec=itemView.findViewById(R.id.brandName);
        TextView brandNamew=itemView.findViewById(R.id.brandName);
        TextView qrandName=itemView.findViewById(R.id.brandName);
        TextView trandName=itemView.findViewById(R.id.brandName);
        TextView yrandName=itemView.findViewById(R.id.brandName);
        TextView grandName=itemView.findViewById(R.id.brandName);


    }
}
