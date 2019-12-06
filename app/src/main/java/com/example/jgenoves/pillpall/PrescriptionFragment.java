package com.example.jgenoves.pillpall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Date;
import java.util.UUID;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PrescriptionFragment extends Fragment {


    private Prescription  mPrescription;
    private static final String ARG_P_ID ="p_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;



    private EditText mNameField;
    private EditText mInstruField;
    private EditText mDosageField;
    private Button mDateButton;



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
        setHasOptionsMenu(true);
    }

    @Override
    public void onPause() {
        super.onPause();

        PrescriptionSingleton.get(getActivity()).updatePrescription(mPrescription);
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
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mPrescription.getFilled());
                dialog.setTargetFragment(PrescriptionFragment.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
            }
        });



        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_prescription, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.delete_prescription:
                PrescriptionSingleton.get(getActivity()).removePrescription(mPrescription);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mPrescription.setFilled(date);
            updateDate();
        }
    }

    private void updateDate(){
        mDateButton.setText(mPrescription.getFilled().toString());
    }

}
