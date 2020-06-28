package com.example.RetailApp.entity;

import java.util.ArrayList;

public class BookingInfo extends BMAObject {

//    public int count ;
//    public ArrayList<EOCustomer> bookings=new ArrayList();
//    public String code;
//    public String result;
    public ArrayList<EOBooking> missedBooking=new ArrayList<>();
    public ArrayList<EOBooking> tomorrowBooking=new ArrayList<>();
    public ArrayList<EOBooking> cancelledBookings=new ArrayList<>();

}
