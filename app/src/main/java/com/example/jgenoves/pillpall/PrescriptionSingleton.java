package com.example.jgenoves.pillpall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jgenoves.pillpall.PrescriptionDBSchema.PrescriptionTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PrescriptionSingleton {

    private static PrescriptionSingleton sPrescriptionSingleton;

    private List<Prescription> mPrescriptions;

    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    public static PrescriptionSingleton get(Context context){
        if(sPrescriptionSingleton == null){
            sPrescriptionSingleton = new PrescriptionSingleton(context);
        }
        return sPrescriptionSingleton;
    }

    private PrescriptionSingleton(){
        mPrescriptions = new ArrayList<>();
    }

    private PrescriptionSingleton(Context context){
        mContext = context.getApplicationContext();
        mSQLiteDatabase = new PrescriptionBaseHelper(mContext)
                .getWritableDatabase();
    }

    public List<Prescription> getPrescriptions(){

        List<Prescription> prescriptions = new ArrayList<>();
        PrescriptionCursorWrapper cursor = queryPrescriptions(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                prescriptions.add(cursor.getPrescription());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return prescriptions;
    }

    public Prescription getPrescription(UUID id){

        PrescriptionCursorWrapper cursor = queryPrescriptions(
                PrescriptionTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getPrescription();
        } finally {
            cursor.close();
        }


    }

    public void addPrescription(Prescription p){

        ContentValues values = getContentValues(p);
        mSQLiteDatabase.insert(PrescriptionTable.NAME, null, values);
    }

    public void updatePrescription(Prescription p){
        String uuidString = p.getId().toString();
        ContentValues values = getContentValues(p);

        mSQLiteDatabase.update(PrescriptionTable.NAME, values,
                PrescriptionTable.Cols.UUID + "= ?",
                new String[] {uuidString});
    }

    public PrescriptionCursorWrapper queryPrescriptions(String whereClause, String[] whereArgs){
        Cursor cursor = mSQLiteDatabase.query(
                PrescriptionTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new PrescriptionCursorWrapper(cursor);
    }

    public void removePrescription(Prescription p){

        String uuidString = p.getId().toString();
        mSQLiteDatabase.delete(PrescriptionTable.NAME,
                PrescriptionTable.Cols.UUID + "= ?",
                new String[] {uuidString});

    }

    private static ContentValues getContentValues(Prescription p){
        ContentValues values = new ContentValues();
        values.put(PrescriptionTable.Cols.UUID, p.getId().toString());
        values.put(PrescriptionTable.Cols.NAME, p.getDrugName());
        values.put(PrescriptionTable.Cols.DOSAGE, p.getDosage());
        values.put(PrescriptionTable.Cols.INSTRUCTIONS, p.getInstructions());
        values.put(PrescriptionTable.Cols.DATE_FILLED, p.getFilled().getTime());

        return values;

    }

}
