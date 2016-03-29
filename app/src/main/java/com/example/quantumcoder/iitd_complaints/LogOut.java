package com.example.quantumcoder.iitd_complaints;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class LogOut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // SessionManager.logoutUser();

        new Handler().postDelayed(new Runnable() {

            /*
            Showing splash screen with a timer. This will be useful when you
             want to show case your app logo / company
             */


            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Successfully Logged Out", LENGTH_LONG).show();
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                // overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                // close this activity
                finish();
            }
        }, 00);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
