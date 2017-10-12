package com.lucaslz.uicomponentexamples;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lucaslz.uicomponentexamples.storage.Address;
import com.lucaslz.uicomponentexamples.storage.AppDatabase;
import com.lucaslz.uicomponentexamples.storage.User;
import com.lucaslz.uicomponentexamples.storage.UserDao;

import java.util.List;

/**
 * Created by lucas on 2017/10/11.
 */
public class DashboardFragment extends Fragment {

    public static final String TAG = DashboardFragment.class.getSimpleName();

    public static DashboardFragment newInstance() {
        Bundle args = new Bundle();

        DashboardFragment fragment = new DashboardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private View.OnClickListener mSaveButtonClickListener = view -> {

        UserDao userDao = AppDatabase.getInstance(getContext()).userDao();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                User user = new User();
                user.setUsername("lucas lee");

                Address address = new Address();
                address.setCity("广州");
                user.setAddress(address);
                userDao.insertAll(user);
                return null;
            }
        }.execute();
    };

    private View.OnClickListener mUpdateButtonClickListener = view -> {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                UserDao userDao = AppDatabase.getInstance(getContext()).userDao();
                List<User> users = userDao.findAll();
                if (users.size() > 0) {
                    User user = users.get(0);
                    user.setUsername("lucaslz");
                    userDao.update(user);
                }
                return null;
            }
        }.execute();
    };

    private View.OnClickListener mDeleteButtonClickListener = view -> {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                UserDao userDao = AppDatabase.getInstance(getContext()).userDao();
                List<User> users = userDao.findAll();
                if (users.size() > 0) {
                    User user = users.get(0);
                    userDao.delete(user);
                }
                return null;
            }
        }.execute();
    };

    private View.OnClickListener mSearchButtonClickListener = view -> {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                UserDao userDao = AppDatabase.getInstance(getContext()).userDao();
                List<User> users = userDao.findAll();
                for (User user : users) {
                    Log.i(TAG, user.getUsername());
                }
                return null;
            }
        }.execute();
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Button saveButton = view.findViewById(R.id.save);
        saveButton.setOnClickListener(mSaveButtonClickListener);

        Button updateButton = view.findViewById(R.id.update);
        updateButton.setOnClickListener(mUpdateButtonClickListener);

        Button deleteButton = view.findViewById(R.id.delete);
        deleteButton.setOnClickListener(mDeleteButtonClickListener);

        Button searchButton = view.findViewById(R.id.search);
        searchButton.setOnClickListener(mSearchButtonClickListener);
        return view;
    }
}
