package com.example.jgenoves.pillpall;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PrescriptionSingleton {

    private static PrescriptionSingleton sPrescriptionSingleton;

    private List<Prescription> mPrescriptions;

    public static PrescriptionSingleton get(Context context){
        if(sPrescriptionSingleton == null){
            sPrescriptionSingleton = new PrescriptionSingleton(context);
        }
        return sPrescriptionSingleton;
    }

    private PrescriptionSingleton(Context context){
        mPrescriptions = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Prescription prescription = new Prescription();
            prescription.setDrugName("Medication #" + i);
            prescription.setDosage("300 mg");
            prescription.setInstructions("Twice daily");
            mPrescriptions.add(prescription);
        }
    }

    public List<Prescription> getPrescriptions(){
        return mPrescriptions;
    }

    public Prescription getPrescription(UUID id){
        for(Prescription p:mPrescriptions){
            if(p.getId().equals(id)){
                return p;
            }
        }

        return null;
    }
}
