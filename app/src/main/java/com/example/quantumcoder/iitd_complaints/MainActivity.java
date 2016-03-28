package com.example.quantumcoder.iitd_complaints;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout mDrawerLayout;
    public NavigationView mNavigationView;
    public FragmentManager mFragmentManager;
    public FragmentTransaction mFragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.quantumcoder.iitd_complaints.R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(com.example.quantumcoder.iitd_complaints.R.id.toolbar);
        setSupportActionBar(toolbar);



        // Setup UI of MainActivity
        setup();

        //callCourse();
        //callGrades(); - called in response of courses request
    }

    void setup(){
        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(com.example.quantumcoder.iitd_complaints.R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(com.example.quantumcoder.iitd_complaints.R.id.shitstuff) ;

        //mNavigationView.addView();
        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the FragmentTabs as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        //mFragmentTransaction = mFragmentManager.beginTransaction();
        //mFragmentTransaction.replace(R.id.containerView, new FragmentThreads()).commit();

        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();


                if (menuItem.getItemId() == com.example.quantumcoder.iitd_complaints.R.id.nav_item_notif) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(com.example.quantumcoder.iitd_complaints.R.id.containerView, new FragmentNotifications()).commit();

                }

                if (menuItem.getItemId() == com.example.quantumcoder.iitd_complaints.R.id.nav_item_home) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(com.example.quantumcoder.iitd_complaints.R.id.containerView, new FragmentHome()).commit();
                }
                if (menuItem.getItemId() == com.example.quantumcoder.iitd_complaints.R.id.nav_item_my_complaints) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(com.example.quantumcoder.iitd_complaints.R.id.containerView, new FragmentMyComplaints()).commit();
                }
                if (menuItem.getItemId() == com.example.quantumcoder.iitd_complaints.R.id.nav_item_add_complaints) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(com.example.quantumcoder.iitd_complaints.R.id.containerView, new FragmentAddComplaints()).commit();
                }
                if (menuItem.getItemId() == com.example.quantumcoder.iitd_complaints.R.id.nav_item_individual_complaints) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(com.example.quantumcoder.iitd_complaints.R.id.containerView, new FragmentIndividualComplaints()).commit();
                }
                if (menuItem.getItemId() == com.example.quantumcoder.iitd_complaints.R.id.nav_item_hostel_complaints) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(com.example.quantumcoder.iitd_complaints.R.id.containerView, new FragmentHostelComplaints()).commit();
                }
                if (menuItem.getItemId() == com.example.quantumcoder.iitd_complaints.R.id.nav_item_institute_complaints) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(com.example.quantumcoder.iitd_complaints.R.id.containerView, new FragmentInstituteComplaints()).commit();
                }
                if (menuItem.getItemId() == com.example.quantumcoder.iitd_complaints.R.id.nav_item_logout) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    FragmentLogout fragment = new FragmentLogout();
                    xfragmentTransaction.attach(fragment); //.commit();

                    xfragmentTransaction.replace(com.example.quantumcoder.iitd_complaints.R.id.containerView, fragment).commit();
                }
                if (menuItem.getItemId() == com.example.quantumcoder.iitd_complaints.R.id.nav_item_profile) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(com.example.quantumcoder.iitd_complaints.R.id.containerView, new FragmentProfile()).commit();
                }
                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(com.example.quantumcoder.iitd_complaints.R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar, com.example.quantumcoder.iitd_complaints.R.string.app_name,
                com.example.quantumcoder.iitd_complaints.R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }



}
