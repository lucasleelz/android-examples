package com.lucaslz.uicomponentexamples;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = (MenuItem item) -> {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ActionBar actionBar = getSupportActionBar();
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragmentManager.beginTransaction()
                        .replace(R.id.navigation_fragment_container, HomeFragment.newInstance())
                        .commit();
                actionBar.setTitle("Home");
                return true;
            case R.id.navigation_dashboard:
                fragmentManager.beginTransaction()
                        .replace(R.id.navigation_fragment_container, HomeFragment.newInstance())
                        .commit();
                actionBar.setTitle("Dashboard");
                return true;
            case R.id.navigation_profile:
                fragmentManager.beginTransaction()
                        .replace(R.id.navigation_fragment_container, ProfileFragment.newInstance())
                        .commit();
                actionBar.setTitle("Profile");
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.navigation_fragment_container, HomeFragment.newInstance())
                .commit();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Home");
    }

}
