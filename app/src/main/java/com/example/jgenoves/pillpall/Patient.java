package com.example.jgenoves.pillpall;

import android.location.Address;

import java.util.Date;
import java.util.Locale;

public class Patient {

    private int mMRN;
    private String mFirstName;
    private String mLastName;
    private Date mDOB;
    private Address mAddress;


    public int getMRN() {
        return mMRN;
    }

    public void setMRN(int MRN) {
        mMRN = MRN;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public Date getDOB() {
        return mDOB;
    }

    public void setDOB(Date DOB) {
        mDOB = DOB;
    }

    public Address getAddress() {
        return mAddress;
    }

    public void setAddress(Address address) {
        mAddress = address;
    }


    public Patient(){
        mMRN = 0;
        mFirstName = "";
        mLastName = "";
        mDOB = new Date();
        mAddress = new Address(Locale.US);
    }

}
