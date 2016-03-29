package com.example.quantumcoder.iitd_complaints;

import android.content.Intent;
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Admin on 26-Mar-16.
 */
public class Signup_Activity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.quantumcoder.iitd_complaints.R.layout.activity_signup);



        Button SignupButton = (Button) findViewById(com.example.quantumcoder.iitd_complaints.R.id.signup_button);
        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText nametext = (EditText) findViewById(R.id.name);
                String name = nametext.getText().toString();
                final EditText usernametext = (EditText) findViewById(R.id.kerberosId);
                String username = usernametext.getText().toString();
                final EditText emailtext = (EditText) findViewById(R.id.Email);
                String email = emailtext.getText().toString();
                final EditText phonetext = (EditText) findViewById(R.id.Phone);
                String phone = phonetext.getText().toString();
                final EditText passtext = (EditText) findViewById(R.id.password);
                String password = passtext.getText().toString();
                final EditText confirmpasstext = (EditText) findViewById(R.id.confirmpassword);
                String password_confirm = confirmpasstext.getText().toString();

                Spinner hostelspinner=(Spinner) findViewById(R.id.user_spinner);
                String hostel = hostelspinner.getSelectedItem().toString();

                String namequery = ""; String emailquery = ""; String passquery = "";
                try {
                    namequery = URLEncoder.encode(name, "utf-8");
                    emailquery = URLEncoder.encode(email, "utf-8");
                    passquery = URLEncoder.encode(password, "utf-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String signup_url = "http://" + LoginActivity.ip + "/General/register/username/"+namequery+"/kerberos/"+username+"/password/"+passquery+"/phone/"+phone+"/email/"+emailquery+"/hostel/"+hostel;
                Log.d(TAG, signup_url);

                //this is entered when the user has just logged in...
                RequestQueue signupRequestQueue = Volley.newRequestQueue(getApplicationContext(), SessionManager.httpStack);
                //requestqueue is made using http-stack as we need to check the sessions of the logged in user
                JsonObjectRequest signupRequest = new JsonObjectRequest
                        (Request.Method.GET, signup_url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(TAG, response.toString());
                                Toast.makeText(getApplicationContext(), "Welcome to IITD complaints app", LENGTH_LONG).show();
                                //Start MainActivity
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                VolleyLog.d(TAG, "Error: " + error.getMessage());
                                //pDialog.setMessage(error.getMessage());
                                Toast.makeText(getApplicationContext(), "Failed to complete signup", LENGTH_LONG).show();
                                //pDialog.setMessage(error.getCause().toString());
                                //pDialog.hide();
                            }
                        });
                signupRequestQueue.add(signupRequest);


            }
        });




        Spinner spinner = (Spinner) findViewById(com.example.quantumcoder.iitd_complaints.R.id.Hostel_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                com.example.quantumcoder.iitd_complaints.R.array.Hostels, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinner1 = (Spinner) findViewById(com.example.quantumcoder.iitd_complaints.R.id.user_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                com.example.quantumcoder.iitd_complaints.R.array.user_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);
    }
}
