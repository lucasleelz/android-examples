package com.lucaslz.criminalintent;

import android.support.v4.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return CrimeListFragment.newInstance();
    }
}
