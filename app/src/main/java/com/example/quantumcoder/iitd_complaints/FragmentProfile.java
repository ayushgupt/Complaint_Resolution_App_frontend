package com.example.quantumcoder.iitd_complaints;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import static com.android.volley.VolleyLog.TAG;


public class FragmentProfile extends Fragment {

    private static final int SPLASH_TIME_OUT = 3000;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(com.example.quantumcoder.iitd_complaints.R.layout.profile_layout,null);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final JSONObject userdata = SessionManager.getUserData();

        final EditText firstnametext = (EditText) view.findViewById(R.id.first_name);
        try {
            firstnametext.setText(userdata.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final EditText hosteltext = (EditText) view.findViewById(R.id.hostel);
        try {
            hosteltext.setText(userdata.getString("hostel"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final EditText emailtext = (EditText) view.findViewById(R.id.Email);
        try {
            emailtext.setText(userdata.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final EditText usernametext = (EditText) view.findViewById(R.id.Username);
        try {
            usernametext.setText(userdata.getString("kerberos_username"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final EditText phonetext = (EditText) view.findViewById(R.id.Phone);
        try {
            phonetext.setText(userdata.getString("phone"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final EditText passwordtext = (EditText) view.findViewById(R.id.password);
        try {
            passwordtext.setText(userdata.getString("password"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Button submitbutton = (Button) view.findViewById(R.id.applychange_button);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                // Cannot edit username
                String username = "";
                try {
                    username = userdata.getString("kerberos_username");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String name = firstnametext.getText().toString().trim();
                String hostel = hosteltext.getText().toString().trim();
                String email = emailtext.getText().toString().trim();
                String emailquery = "";
                try {
                    emailquery = URLEncoder.encode(email,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String phone = phonetext.getText().toString().trim();
                String password = passwordtext.getText().toString();

                String editprofile_url = "http://"+LoginActivity.ip+"/User/editinfo/username/"+username+"/hostel/"+hostel+"/email/"+emailquery+"/phone/"+phone+"/password/"+password;
                Log.d(TAG, editprofile_url);

                //this is entered when the user has just logged in...
                RequestQueue editProfileRequestQueue = Volley.newRequestQueue(getContext(), SessionManager.httpStack);
                //requestqueue is made using http-stack as we need to check the sessions of the logged in user
                JsonObjectRequest editProfileRequest = new JsonObjectRequest
                        (Request.Method.GET, editprofile_url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(TAG, response.toString());
                                new Handler().postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        // This method will be executed once the timer is over
                                        // Start your app main activity
                                        Intent i = new Intent(getActivity(), MainActivity.class);
                                        startActivity(i);
                                        // overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                                        getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                                        // close this activity
                                        getActivity().finish();
                                    }
                                }, SPLASH_TIME_OUT);



                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                VolleyLog.d(TAG, "Error: " + error.getMessage());
                                //pDialog.setMessage(error.getMessage());
                                Toast.makeText(getContext(), "Failed to update profile data", LENGTH_LONG).show();
                                //pDialog.setMessage(error.getCause().toString());
                                //pDialog.hide();
                            }
                        });
                editProfileRequestQueue.add(editProfileRequest);



            }
        });

    }
}
