package com.example.jgenoves.pillpall;

import java.util.Date;
import java.util.UUID;

public class Prescription {


    private UUID mId;
    private String mDrugName;
    private String mDosage;
    private Date mFilled;
    private String mInstructions;



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


    public String getInstructions() {
        return mInstructions;
    }

    public void setInstructions(String instructions) {
        mInstructions = instructions;
    }

    public Date getFilled() {
        return mFilled;
    }

    public void setFilled(Date filled) {
        mFilled = filled;
    }


    public Prescription(){
        mId = UUID.randomUUID();
        mFilled = new Date();

    }




    public Prescription(UUID id){
        mId = id;
        mFilled = new Date();


    }


}
