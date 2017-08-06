package com.lucaslz.criminalintent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyCrimeRecyclerViewAdapter extends RecyclerView.Adapter<MyCrimeRecyclerViewAdapter.ViewHolder> {

    private final List<Crime> mCrimes;

    public MyCrimeRecyclerViewAdapter(List<Crime> crimes) {
        mCrimes = crimes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_crime_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mCrime = mCrimes.get(position);
        holder.mIdView.setText(mCrimes.get(position).getId().toString());
        holder.mContentView.setText(mCrimes.get(position).getDate().toString());

        holder.mView.setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        return mCrimes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Crime mCrime;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.id);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
