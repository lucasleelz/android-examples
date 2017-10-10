package com.lucaslz.uicomponentexamples;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lucaslz.component.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnBottomNavigationItemSelectedListener = (MenuItem item) -> {
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

    private NavigationView.OnNavigationItemSelectedListener mOnNavigationViewItemSelectedListener = (MenuItem item) -> {
        int selectedItemId = item.getItemId();
        item.setCheckable(true);
        item.setChecked(true);
        if (selectedItemId == R.id.nav_camera) {
            // Handle the camera action
        } else if (selectedItemId == R.id.nav_gallery) {

        } else if (selectedItemId == R.id.nav_slideshow) {

        } else if (selectedItemId == R.id.nav_manage) {

        } else if (selectedItemId == R.id.nav_share) {

        } else if (selectedItemId == R.id.nav_send) {
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    };

    private View.OnClickListener mFabOnClickListener = (View view) -> {
        Snackbar.make(view, "添加数据", Snackbar.LENGTH_SHORT)
                .setAction("撤销", (undoView) ->
                        Toast.makeText(this, "数据已存储", Toast.LENGTH_SHORT).show()
                ).show();

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnBottomNavigationItemSelectedListener);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(mOnNavigationViewItemSelectedListener);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(mFabOnClickListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.navigation_fragment_container, HomeFragment.newInstance())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
