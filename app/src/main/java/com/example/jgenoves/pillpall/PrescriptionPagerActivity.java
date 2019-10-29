package com.example.jgenoves.pillpall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;
import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class PrescriptionPagerActivity extends AppCompatActivity {

    private static final String EXTRA_P_ID =
            "com.example.jgenoves.pillpall.p_id";

    private ViewPager mViewPager;
    private List<Prescription> mPrescriptions;

    public static Intent newIntent(Context packageContext, UUID pId){
        Intent intent = new Intent(packageContext, PrescriptionPagerActivity.class);
        intent.putExtra(EXTRA_P_ID, pId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstaneState){
        super.onCreate(savedInstaneState);
        setContentView(R.layout.activity_prescription_pager);

        UUID pId = (UUID) getIntent().getSerializableExtra(EXTRA_P_ID);

        mViewPager = (ViewPager) findViewById(R.id.p_view_pager);

        mPrescriptions = PrescriptionSingleton.get(this).getPrescriptions();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Prescription prescription = mPrescriptions.get(position);
                return PrescriptionFragment.newInstance(prescription.getId());
            }

            @Override
            public int getCount() {
                return mPrescriptions.size();
            }
        });

        for(int i = 0; i < mPrescriptions.size(); i++){
            if(mPrescriptions.get(i).getId().equals(pId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
//    public static final String EXTRA_P_ID =
//            "com.example.jgenoves.pillpall";
//
//    public static Intent newIntent(Context packageContext, UUID pId){
//        Intent intent = new Intent(packageContext, PrescriptionPagerActivity.class);
//        intent.putExtra(EXTRA_P_ID, pId);
//        return intent;
//    }
//   @Override
//    protected Fragment createFragment() {
//       UUID pId = (UUID) getIntent().getSerializableExtra(EXTRA_P_ID);
//       return PrescriptionFragment.newInstance(pId);
//   }

}
