package com.lucaslz.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity
        implements CrimeFragment.Callbacks {

    private static final String EXTRA_CRIME_ID = "com.lucaslz.criminalintent.CrimePagerActivity.crime_id";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent result = new Intent(packageContext, CrimePagerActivity.class);
        result.putExtra(EXTRA_CRIME_ID, crimeId);
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mViewPager = findViewById(R.id.activity_crime_pager_view_pager);
        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        for (int index = 0; index < mCrimes.size(); index++) {
            if (mCrimes.get(index).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(index);
                break;
            }
        }
    }

    @Override
    public void onCrimeUpdated(Crime crime) {
    }
}
