package com.example.jgenoves.pillpall;

import java.util.Date;
import java.util.UUID;

public class Prescription {


    private UUID mId;
    private String mDrugName;
    private String mDosage;
    private int mQuantity;
    private int mDays;
    private String mPrescriber;
    private Date mFilled;
    private Date mWritten;
    private String mInstructions;
    private boolean mIsActive;



    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }


    public String getDrugName() {
        return mDrugName;
    }

    public void setDrugName(String drugName) {
        mDrugName = drugName;
    }

    public String getDosage() {
        return mDosage;
    }

    public void setDosage(String dosage) {
        mDosage = dosage;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public int getDays() {
        return mDays;
    }

    public void setDays(int days) {
        mDays = days;
    }

    public String getPrescriber() {
        return mPrescriber;
    }

    public void setPrescriber(String prescriber) {
        mPrescriber = prescriber;
    }

    public Date getFilled() {
        return mFilled;
    }

    public void setFilled(Date filled) {
        mFilled = filled;
    }

    public Date getWritten() {
        return mWritten;
    }

    public void setWritten(Date written) {
        mWritten = written;
    }

    public String getInstructions() {
        return mInstructions;
    }

    public void setInstructions(String instructions) {
        mInstructions = instructions;
    }

    public boolean isActive() {
        return mIsActive;
    }

    public void setActive(boolean active) {
        mIsActive = active;
    }



    public Prescription(){
        mId = UUID.randomUUID();
        mFilled = new Date();

    }


}
