package com.lucaslz.criminalintent;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeRecyclerViewAdapter extends RecyclerView.Adapter<CrimeRecyclerViewAdapter.CrimeHolder> {

    private List<Crime> mCrimes;
    private CrimeListFragment.Callbacks mCallbacks;

    public CrimeRecyclerViewAdapter(List<Crime> crimes, CrimeListFragment.Callbacks callbacks) {
        mCrimes = crimes;
        mCallbacks = callbacks;
    }

    @Override
    public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_crime_item, parent, false);
        return new CrimeHolder(view);
    }

    @Override
    public void onBindViewHolder(final CrimeHolder holder, int position) {
        Crime crime = mCrimes.get(position);
        holder.bind(crime);
    }

    @Override
    public int getItemCount() {
        return mCrimes.size();
    }

    public void setCrimes(List<Crime> crimes) {
        mCrimes = crimes;
    }

    final public class CrimeHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mCrimeTitleTextView;
        private final TextView mCrimeDateTextView;
        private final ImageView mIsSolvedImageView;

        private Crime mCrime;

        public CrimeHolder(View view) {
            super(view);
            mView = view;
            mCrimeTitleTextView = view.findViewById(R.id.crime_title_text_view);
            mCrimeDateTextView = view.findViewById(R.id.crime_date_text_view);
            mIsSolvedImageView = view.findViewById(R.id.is_solved_image_view);
        }

        public void bind(Crime crime) {
            mCrime = crime;

            mCrimeTitleTextView.setText(mCrime.getTitle());
            mCrimeDateTextView.setText(mCrime.getFormatDateString());

            mView.setOnClickListener(view -> {
                mCallbacks.onCrimeSelected(crime);
            });

            mIsSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mCrimeDateTextView.getText() + "'";
        }
    }
}
