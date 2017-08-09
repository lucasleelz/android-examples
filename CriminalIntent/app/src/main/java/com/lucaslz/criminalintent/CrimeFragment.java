package com.lucaslz.criminalintent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import java.util.Date;
import java.util.UUID;

public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    private Crime mCrime;

    private EditText mCrimeTitleEditText;

    private Button mCrimeDateButton;

    private CheckBox mCrimeSolvedCheckBox;

    private Button mReportButton;

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
    public void onPause() {
        super.onPause();
        CrimeLab.get(getActivity()).updateCrime(mCrime);
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
            DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
            dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
            dialog.show(manager, DIALOG_DATE);
        });
        mCrimeSolvedCheckBox = result.findViewById(R.id.crime_solved_check_box);
        addCheckedChangeListener();

        mCrimeTitleEditText.setText(mCrime.getTitle());
        updateDate();
        mCrimeSolvedCheckBox.setChecked(mCrime.isSolved());

        mReportButton = result.findViewById(R.id.crime_report_button);
        mReportButton.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, getCrimeReport());
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.crime_report_suspect));

            intent = Intent.createChooser(intent, getString(R.string.send_report));
            startActivity(intent);
        });

        return result;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode != REQUEST_DATE) {
            return;
        }
        Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
        mCrime.setDate(date);
        updateDate();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void updateDate() {
        mCrimeDateButton.setText(mCrime.getFormatDateString());
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

    private String getCrimeReport() {
        String solvedString = getString(
                mCrime.isSolved()
                        ? R.string.crime_report_solved
                        : R.string.crime_report_unsolved
        );

        String suspect = getString(
                mCrime.getSuspect() == null
                        ? R.string.crime_report_no_suspect
                        : R.string.crime_report_suspect
        );
        return getString(
                R.string.crime_report,
                mCrime.getTitle(),
                mCrime.getFormatDateString(),
                solvedString,
                suspect
        );
    }
}
