package com.example.jgenoves.pillpall;

public class PrescriptionDBSchema {

    public static final class PrescriptionTable {
        public static final String NAME ="prescriptions";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String DATE_FILLED = "date_filled";
            public static final String INSTRUCTIONS = "instructions";
            public static final String DOSAGE = "dosage";

        }
    }
}
