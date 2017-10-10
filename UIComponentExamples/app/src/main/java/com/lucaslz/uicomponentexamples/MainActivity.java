package com.lucaslz.uicomponentexamples;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lucaslz.component.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;

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

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Home");
        setSupportActionBar(mToolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.navigation_fragment_container, HomeFragment.newInstance())
                .commit();
    }
}
