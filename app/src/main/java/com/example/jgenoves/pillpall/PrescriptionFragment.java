package com.example.jgenoves.pillpall;

import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.UUID;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PrescriptionFragment extends Fragment {

    private Prescription  mPrescription;
    private static final String ARG_P_ID ="p_id";

    private EditText mNameField;
    private EditText mInstruField;
    private EditText mDosageField;
    private Button mDateButton;
    private CheckBox mIsActiveCheckbox;


    public static PrescriptionFragment newInstance(UUID pId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_P_ID, pId);

        PrescriptionFragment fragment =  new PrescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanecState){
        super.onCreate(savedInstanecState);
        UUID pId = (UUID) getArguments().getSerializable(ARG_P_ID);
        mPrescription = PrescriptionSingleton.get(getActivity()).getPrescription(pId);
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_prescription, container, false);

        mNameField = (EditText) v.findViewById(R.id.p_name);
        mNameField.setText(mPrescription.getDrugName());
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPrescription.setDrugName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mInstruField = (EditText) v.findViewById(R.id.p_instructions);
        mInstruField.setText(mPrescription.getInstructions());
        mInstruField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPrescription.setInstructions(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDosageField = (EditText) v.findViewById(R.id.p_dosage);
        mDosageField.setText(mPrescription.getDosage());
        mDosageField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPrescription.setDosage(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mDateButton = (Button) v.findViewById(R.id.p_fill_date);
        mDateButton.setText(mPrescription.getFilled().toString());
        mDateButton.setEnabled(false);


        mIsActiveCheckbox = (CheckBox) v.findViewById(R.id.p_is_active);
        mIsActiveCheckbox.setChecked(mPrescription.isActive());
        mIsActiveCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked){
                mPrescription.setActive(isChecked);
            }

        });


        return v;
    }

}
