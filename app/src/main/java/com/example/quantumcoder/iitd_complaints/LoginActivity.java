package com.example.quantumcoder.iitd_complaints;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.*;
import static android.widget.Toast.LENGTH_LONG;
import static com.android.volley.VolleyLog.TAG;


/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends AppCompatActivity {
    static final String ip = "192.168.170.51/iitd_complaints_server/index.php";

    // UI references.
    private EditText mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.quantumcoder.iitd_complaints.R.layout.activity_login);

        // Session Manager
        new SessionManager(getApplicationContext());

        // Set up the login form.
        mUsernameView = (EditText) findViewById(com.example.quantumcoder.iitd_complaints.R.id.username);

        mPasswordView = (EditText) findViewById(com.example.quantumcoder.iitd_complaints.R.id.password);

        mLoginFormView = findViewById(com.example.quantumcoder.iitd_complaints.R.id.login_form);
        mProgressView = findViewById(com.example.quantumcoder.iitd_complaints.R.id.login_progress);

        Button LoginButton = (Button) findViewById(com.example.quantumcoder.iitd_complaints.R.id.login_button);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform validation here again to prevent submission of invalid fields
                if (checkValidation()) {
                    //Toast.makeText(LoginActivity.this, "Attempting to login", LENGTH_LONG).show();
                    attemptLogin();
                }

            }
        });

        Button SigninButton = (Button) findViewById(com.example.quantumcoder.iitd_complaints.R.id.signup_button);
        SigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Main Activity
                Intent i = new Intent(getApplicationContext(), Signup_Activity.class);
                startActivity(i);

            }
        });

        //if(!isNetworkConnected()){ makeText(LoginActivity.this, "Please connect to network", LENGTH_LONG).show(); }


        Spinner spinner = (Spinner) findViewById(com.example.quantumcoder.iitd_complaints.R.id.planets_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                com.example.quantumcoder.iitd_complaints.R.array.user_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }


    private void attemptLogin() {
        /* Form is validated, can be submitted now */

        if(!isNetworkConnected()){ makeText(LoginActivity.this, "Please connect to network", LENGTH_LONG).show(); }

        final String username = mUsernameView.getText().toString().trim();
        final String password = mPasswordView.getText().toString().trim();
        String url = String.format("http://"+LoginActivity.ip+"/General/login/username/%s/password/%s",username,password);
        Log.d(TAG, url);

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue( getApplicationContext(), SessionManager.httpStack  );

        JsonObjectRequest loginRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        //pDialog.setMessage(response.toString());
                        try {
                            pDialog.hide();
                            String str = response.getString("success") ;
                            if(str.equals("true")){

                                // Create cookies - implicitly passed to the httpUrlConnection
                                SessionManager.createLoginSession(username, password);
                                SessionManager.setUserData((JSONObject) response.get("user"));

                                Toast.makeText(getApplicationContext(), "Login Successful.", LENGTH_SHORT).show();
                                // Starting MainActivity
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Invalid username or password", LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        //pDialog.setMessage(error.getMessage());
                        Toast.makeText(getApplicationContext(),"Login failed.", LENGTH_LONG).show();
                        //pDialog.setMessage(error.getCause().toString());
                        pDialog.hide();

                    }
                });

             requestQueue.add(loginRequest);
    }


    //function for validating form data
    private boolean checkValidation() {
        if(!Validation.isUsername(mUsernameView)){ makeText(LoginActivity.this, "Invalid username", LENGTH_LONG).show(); return false; }

        return true;
    }


    // Check if connected to network (internet assumed)
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


}

