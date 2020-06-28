package com.example.RetailApp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.RetailApp.R;
import com.example.RetailApp.adapter.BMARecyclerAdapter;
import com.example.RetailApp.component.BMAAlertDialog;
import com.example.RetailApp.entity.EOBooking;
import com.example.RetailApp.entity.EOEscalationReason;

import org.json.JSONObject;

import java.util.ArrayList;

import helper.BMAGson;
import helper.HttpRequest;
import util.BMAConstants;
import util.BMAUIUtil;

public class EscalationFragment extends BMAFragment {
    Button save;
    EditText Problemdescriptionedittext;
    RecyclerView excalationReasonRecyclerView;
    EOBooking eoBooking;
    TextView bookingID,vendorName,status;
    ArrayList<EOEscalationReason>escalationReasonArrayList=new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.eoBooking = getArguments().getParcelable("eoBooking");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.escalation_reason, container, false);
        this.save=this.view.findViewById(R.id.save);
        this.Problemdescriptionedittext=this.view.findViewById(R.id.Problemdescriptionedittext);
        this.excalationReasonRecyclerView=this.view.findViewById(R.id.escalationReasonRecyclerView);
        bookingID=this.view.findViewById(R.id.bookingID);
        vendorName=this.view.findViewById(R.id.vendorName);
        status=this.view.findViewById(R.id.status);
        bookingID.setText(eoBooking.bookingID);
        vendorName.setText(eoBooking.name);
        status.setText(eoBooking.current_status);
        BMAUIUtil.setBackgroundRect(this.save,R.color.notifIconColor, R.dimen._50dp);
        this.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitEscaltion();
            }
        });
        getEscalationnReason();
        return this.view;
    }

    // entity send to login based
    private void getEscalationnReason(){
        httpRequest = new HttpRequest(getMainActivity(), true);
        httpRequest.delegate = EscalationFragment.this;
        actionID="getEscalationReason";
        httpRequest.execute(actionID,"247around");
    }
    private void submitEscaltion(){
        if(checkAllFieldEmpty()){
            submitEscaltionRequest();
        }
    }
    String reasonId;
    private boolean checkAllFieldEmpty(){
        if(this.Problemdescriptionedittext.getText().toString().trim().length()==0){
            Toast.makeText(getContext(),"Please enter remarks",Toast.LENGTH_SHORT).show();
            return false;
        }
        boolean ischecked=false;
        for(EOEscalationReason eoEscalationReason:this.escalationReasonArrayList){
            if(eoEscalationReason.isChecked){
                ischecked= true;
                reasonId=eoEscalationReason.id;
            }
        }
        if(!ischecked){
            Toast.makeText(getContext(),"Please select reason",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    HttpRequest httpRequest;
    private void submitEscaltionRequest(){
        httpRequest = new HttpRequest(getMainActivity(), true);
        httpRequest.delegate = EscalationFragment.this;
        actionID="submitEscalation";
        httpRequest.execute(actionID, "247around",this.eoBooking.bookingID,reasonId,Problemdescriptionedittext.getText().toString().trim());
    }
    String actionID;

    @Override
    public void processFinish(String response) {
        super.processFinish(response);
        Log.d("aaaaa","responseeeee ="+response);
        httpRequest.progress.dismiss();
        if (response.contains("data")) {
            JSONObject jsonObjectHttpReq;

            try {
                jsonObjectHttpReq = new JSONObject(response);

                final JSONObject jsonObject = jsonObjectHttpReq.getJSONObject("data");
                String statusCode = jsonObject.getString("code");
                if (statusCode.equals("0000")) {
                    if(this.actionID.equalsIgnoreCase("getEscalationReason")) {
                        JSONObject escalationResponse = jsonObject.getJSONObject("response");
                        if (escalationResponse != null) {

                            escalationReasonArrayList = BMAGson.store().getList(EOEscalationReason.class, escalationResponse.getString("escalation_reason"));
                            datatoView();
                        }
                    }else if(this.actionID.equalsIgnoreCase("submitEscalation")){
                        BMAAlertDialog bmaAlertDialog = new BMAAlertDialog(getContext()) {


                            @Override
                            public void onDefault() {
                                super.onDefault();

                                getTargetFragment().onActivityResult(getTargetRequestCode(), BMAConstants.requestCode, null);
                                getFragmentManager().popBackStack();
                            }

                            @Override
                            public void onConfirmation() {
                                super.onConfirmation();

                            }
                        };
                        bmaAlertDialog.show(jsonObject.get("result").toString());
                    }
                   // escalation_reasonB
                }
            } catch (Exception e) {

            }
        }
    }
    BMARecyclerAdapter bmaRecyclerAdapter;
    private void datatoView(){
        bmaRecyclerAdapter = new BMARecyclerAdapter(getContext(), this.escalationReasonArrayList, excalationReasonRecyclerView, this, R.layout.singleselection_item_row);
        excalationReasonRecyclerView.setHasFixedSize(true);
        excalationReasonRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        excalationReasonRecyclerView.setAdapter(bmaRecyclerAdapter);

    }

    @Override
    public <T> void createRow(RecyclerView.ViewHolder viewHolder, View itemView, T rowObject, int position) {

        final EOEscalationReason eoEscalationReason= (EOEscalationReason) rowObject;
        TextView staus=itemView.findViewById(R.id.staus);
        AppCompatRadioButton radioButton=itemView.findViewById(R.id.radioButton);
        staus.setText(eoEscalationReason.escalation_reason);
            radioButton.setChecked(eoEscalationReason.isChecked);
            radioButton.setTag(eoEscalationReason);
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EOEscalationReason eoEscalationReason1= (EOEscalationReason) v.getTag();
                    AppCompatRadioButton appCompatRadioButton= (AppCompatRadioButton) v;
                    for(EOEscalationReason eoEscalationReason2:escalationReasonArrayList) {
                        if(appCompatRadioButton.isChecked() && eoEscalationReason2.id.equalsIgnoreCase(eoEscalationReason1.id)) {
                            eoEscalationReason1.isChecked = true;
                        }else{
                            eoEscalationReason2.isChecked=false;
                        }
                    }
                    bmaRecyclerAdapter.notifyDataSetChanged();
                }
            });

    }
}
