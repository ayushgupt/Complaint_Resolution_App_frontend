package com.example.quantumcoder.iitd_complaints;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

import static android.widget.Toast.LENGTH_LONG;
import static com.android.volley.VolleyLog.TAG;

public class DetailedComplaintActivity extends AppCompatActivity {

    JSONObject complaint_specific_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_complaint);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int complaint_id = 1;//MainActivity.selected_complaint;
        String indi_complaint_url = ("http://"+LoginActivity.ip + "/User/indicomplaint/id/"+complaint_id ).trim();
        Log.d(TAG, indi_complaint_url);

        //this is entered when the user has just logged in...
        RequestQueue indiComplaintRequestQueue = Volley.newRequestQueue(getApplicationContext(), SessionManager.httpStack);
        //requestqueue is made using http-stack as we need to check the sessions of the logged in user
        JsonObjectRequest indiComplaintRequest = new JsonObjectRequest
                (Request.Method.GET, indi_complaint_url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        complaint_specific_data = response;

                        String complaint_title = "";
                        TextView titleview = (TextView) findViewById(R.id.complaint_title);
                        try {
                            complaint_title = complaint_specific_data.getJSONObject("complaint").getString("title");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        titleview.setText(complaint_title);

                        String description = "";
                        TextView descriptionview = (TextView) findViewById(R.id.complaint_description);
                        try {
                            description = complaint_specific_data.getJSONObject("complaint").getString("decription");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        descriptionview.setText(description);

                        String created_at = "";
                        TextView createdview = (TextView) findViewById(R.id.complaint_time);
                        try {
                            created_at = complaint_specific_data.getJSONObject("complaint").getString("datetime_created");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        createdview.setText(created_at);

                        displayListView();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        //pDialog.setMessage(error.getMessage());
                        Toast.makeText(getApplicationContext(), "Failed to fetch complaint data", LENGTH_LONG).show();
                        //pDialog.setMessage(error.getCause().toString());
                        //pDialog.hide();
                    }
                });
        indiComplaintRequestQueue.add(indiComplaintRequest);

    }

    private void displayListView() {

    }

}
