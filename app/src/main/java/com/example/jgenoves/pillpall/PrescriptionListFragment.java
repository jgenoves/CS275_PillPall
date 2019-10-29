package com.example.jgenoves.pillpall;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PrescriptionListFragment extends Fragment {

    private RecyclerView mPresciptionRecyclerView;
    private PrescriptionAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_prescription_list, container, false);

        mPresciptionRecyclerView = (RecyclerView) v
                .findViewById(R.id.plist_recycler_view);
        mPresciptionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        PrescriptionSingleton pLab = PrescriptionSingleton.get(getActivity());
        List<Prescription> prescriptions = pLab.getPrescriptions();
        mAdapter = new PrescriptionAdapter(prescriptions);
        mPresciptionRecyclerView.setAdapter(mAdapter);

        if(mAdapter == null){
            mAdapter = new PrescriptionAdapter(prescriptions);
            mPresciptionRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }






    //------------------- PRESCRIPTION HOLDER ------------------------------
    private class PrescriptionHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

        private TextView mName;
        private TextView mDosage;
        private TextView mInstructions;
        private TextView mDateFilled;

        private Prescription mPrescription;


        public PrescriptionHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_prescription, parent, false));

            mName = (TextView) itemView.findViewById(R.id.p_name);
            mDosage = (TextView) itemView.findViewById(R.id.p_dosage);
            mInstructions = (TextView) itemView.findViewById(R.id.p_instructions);
            mDateFilled = (TextView) itemView.findViewById(R.id.p_fill_date);

            itemView.setOnClickListener(this);
        }

        public void bind(Prescription prescription){
            mPrescription = prescription;
            mName.setText(prescription.getDrugName());
            mDosage.setText(prescription.getDosage());
            mInstructions.setText(prescription.getInstructions());
            mDateFilled.setText("Date Filled: "+ prescription.getFilled().toString());
        }

        @Override
        public void onClick(View view){
            Intent intent = PrescriptionPagerActivity.newIntent(getActivity(), mPrescription.getId());
            startActivity(intent);
        }

    }








    //--------------- PRESCRIPTION ADAPTER -------------------------------------------------
    private class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionHolder>{
        private List<Prescription> mPrescriptions;

        public PrescriptionAdapter(List<Prescription> prescriptions){
            mPrescriptions = prescriptions;
        }


        @NonNull
        @Override
        public PrescriptionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new PrescriptionHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PrescriptionHolder holder, int position) {

            Prescription p = mPrescriptions.get(position);
            holder.bind(p);

        }

        @Override
        public int getItemCount() {
            return mPrescriptions.size();
        }
    }


}
