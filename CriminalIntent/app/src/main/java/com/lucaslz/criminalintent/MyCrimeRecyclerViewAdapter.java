package com.lucaslz.criminalintent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyCrimeRecyclerViewAdapter extends RecyclerView.Adapter<MyCrimeRecyclerViewAdapter.CrimeHolder> {

    private final List<Crime> mCrimes;

    public MyCrimeRecyclerViewAdapter(List<Crime> crimes) {
        mCrimes = crimes;
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

    final public class CrimeHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mCrimeTitleTextView;
        private final TextView mCrimeDateTextView;
        private Crime mCrime;

        public CrimeHolder(View view) {
            super(view);
            mView = view;
            mCrimeTitleTextView = view.findViewById(R.id.crime_title_text_view);
            mCrimeDateTextView = view.findViewById(R.id.crime_date_text_view);
        }

        public void bind(Crime crime) {
            mCrime = crime;

            mCrimeTitleTextView.setText(mCrime.getTitle());
            mCrimeDateTextView.setText(mCrime.getDate().toString());

            mView.setOnClickListener(view -> {
                Toast.makeText(view.getContext(), mCrime.getTitle() + "Clicked !", Toast.LENGTH_LONG).show();
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mCrimeDateTextView.getText() + "'";
        }
    }
}
