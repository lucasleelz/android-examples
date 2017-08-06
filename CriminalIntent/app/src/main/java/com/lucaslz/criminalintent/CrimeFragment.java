package com.lucaslz.criminalintent;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";

    private Crime mCrime;

    private EditText mCrimeTitleEditText;

    private Button mCrimeDateButton;

    private CheckBox mCrimeSolvedCheckBox;

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_crime, container, false);
        mCrimeTitleEditText = result.findViewById(R.id.crime_title_edit_text);
        addTextChangedListener();

        mCrimeDateButton = result.findViewById(R.id.crime_date_button);
        mCrimeDateButton.setText(mCrime.getDate().toString());
        mCrimeDateButton.setOnClickListener(view -> {
            FragmentManager manager = getFragmentManager();
            DatePickerFragment dialog = new DatePickerFragment();
            dialog.show(manager, DIALOG_DATE);
        });
        mCrimeSolvedCheckBox = result.findViewById(R.id.crime_solved_check_box);
        addCheckedChangeListener();

        mCrimeTitleEditText.setText(mCrime.getTitle());
        mCrimeDateButton.setText(mCrime.getFormatDateString());
        mCrimeSolvedCheckBox.setChecked(mCrime.isSolved());

        return result;
    }

    private void addCheckedChangeListener() {
        mCrimeSolvedCheckBox.setOnCheckedChangeListener((CompoundButton compoundButton, boolean b) -> {
            mCrime.setSolved(b);
        });
    }

    private void addTextChangedListener() {
        mCrimeTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
