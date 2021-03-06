package com.example.RetailApp.component;

import com.example.RetailApp.entity.BMAObject;
import com.google.gson.annotations.SerializedName;

public class EOProfile extends BMAObject {
    public long id;
    public String name;
    public String phone;
    @SerializedName("alternate_phone")
    public String alternatePhone;
    @SerializedName("service_center_id")
    public long serviceCenterID;
    public int active;
    public int delete;
    public String create_date;
    public String update_date;
    public String appliances;
    @SerializedName("identity_proof_type")
    public String identityProofType;
    @SerializedName("identity_proof_number")
    public String identityProofNumber;
    @SerializedName("company_name")
    public String serviceCenterName;
    public String district;
}
