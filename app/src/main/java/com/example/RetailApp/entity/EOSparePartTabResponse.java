package com.example.RetailApp.entity;

import java.util.ArrayList;

public class EOSparePartTabResponse extends BMAObject {
    public ArrayList<EOSpare>spare_requested=new ArrayList<>();
    public ArrayList<EOSpare>spare_shipped=new ArrayList<>();
    public ArrayList<EOSpare>spare_defective=new ArrayList<>();
    public ArrayList<EOSpare>spare_invoice=new ArrayList<>();
    public ArrayList<EOSpare>spare_oow=new ArrayList<>();
    public ArrayList<EOSpare>estimate_given=new ArrayList<>();




}
