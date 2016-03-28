package com.example.quantumcoder.iitd_complaints;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class detail_comp_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.quantumcoder.iitd_complaints.R.layout.activity_detail_comp_activity);
        Toolbar toolbar = (Toolbar) findViewById(com.example.quantumcoder.iitd_complaints.R.id.toolbar);
        setSupportActionBar(toolbar);


    }

}
