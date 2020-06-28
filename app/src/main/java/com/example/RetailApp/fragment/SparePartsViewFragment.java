package com.example.RetailApp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.RetailApp.R;

public class SparePartsViewFragment extends BMAFragment {
    LinearLayout sparerequstedbysfLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.sparepart_tab_fragment, container, false);
        this.sparerequstedbysfLayout=this.view.findViewById(R.id.sparerequstedbysfLayout);
        this.sparerequstedbysfLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return this.view;
    }
}
