package com.example.jgenoves.pillpall;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.jgenoves.pillpall.PrescriptionDBSchema.PrescriptionTable;

import java.util.Date;
import java.util.UUID;

public class PrescriptionCursorWrapper extends CursorWrapper {

    public PrescriptionCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Prescription getPrescription() {
        String uuidString = getString(getColumnIndex(PrescriptionTable.Cols.UUID));
        String name = getString(getColumnIndex(PrescriptionTable.Cols.NAME));
        String instructions = getString(getColumnIndex(PrescriptionTable.Cols.INSTRUCTIONS));
        String dosage = getString(getColumnIndex(PrescriptionTable.Cols.DOSAGE));
        long dateFilled = getLong(getColumnIndex(PrescriptionTable.Cols.DATE_FILLED));

        Prescription p = new Prescription(UUID.fromString(uuidString));
        p.setDosage(dosage);
        p.setDrugName(name);
        p.setInstructions(instructions);
        p.setFilled(new Date(dateFilled));
        return p;
    }
}
