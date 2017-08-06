package com.lucaslz.criminalintent;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.PhantomReference;

public class CrimeListFragment extends Fragment {

    private CrimeLab mCrimeLab = CrimeLab.get(getActivity());

    private CrimeRecyclerViewAdapter mCrimeRecyclerViewAdapter;

    private RecyclerView mRecyclerView;

    public CrimeListFragment() {
    }

    public static CrimeListFragment newInstance() {
        CrimeListFragment fragment = new CrimeListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        if (view instanceof RecyclerView) {
            mRecyclerView = (RecyclerView) view;
            updateUI();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        updateUI();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void updateUI() {
        if (mCrimeRecyclerViewAdapter == null) {
            mCrimeRecyclerViewAdapter = new CrimeRecyclerViewAdapter(mCrimeLab.getCrimes());
            mRecyclerView.setAdapter(mCrimeRecyclerViewAdapter);
        } else {
            mCrimeRecyclerViewAdapter.notifyDataSetChanged();
        }
    }
}
