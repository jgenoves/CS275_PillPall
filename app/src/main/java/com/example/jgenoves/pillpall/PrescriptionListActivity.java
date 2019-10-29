package com.example.jgenoves.pillpall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class PrescriptionListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new PrescriptionListFragment();
    }
}
