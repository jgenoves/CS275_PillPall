package com.example.jgenoves.pillpall;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jgenoves.pillpall.PrescriptionDBSchema.PrescriptionTable;


public class PrescriptionBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "prescriptions.db";

    public PrescriptionBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + PrescriptionTable.NAME + "(" +
                "_id integer primary key autoIncrement, " +
                PrescriptionTable.Cols.UUID + ", " +
                PrescriptionTable.Cols.NAME + ", " +
                PrescriptionTable.Cols.DOSAGE + ", "+
                PrescriptionTable.Cols.DATE_FILLED + ", " +
                PrescriptionTable.Cols.INSTRUCTIONS +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
