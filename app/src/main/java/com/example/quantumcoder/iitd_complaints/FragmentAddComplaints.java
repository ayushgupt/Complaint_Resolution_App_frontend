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
import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Admin on 26-Mar-16.
 */
public class FragmentAddComplaints extends Fragment{
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_complaint,null);



    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner spinner = (Spinner) getView().findViewById(R.id.complaint_level);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.c_level, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        final Button addButton = (Button) view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                final EditText titletext = (EditText) view.findViewById(R.id.title);
                String title = titletext.getText().toString();
                final EditText descriptiontext = (EditText) view.findViewById(R.id.description);
                String description = descriptiontext.getText().toString();
                String username = ""; String hostel = "";
                try {
                    username = SessionManager.getUserData().getString("kerberos_username");
                    hostel = SessionManager.getUserData().getString("hostel");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Spinner complaint_type_spinner =(Spinner) view.findViewById(R.id.complaint_level);
                String complaint_type = "1";

                String complaint_admin = "";
                switch(complaint_type){
                    case "0" : complaint_admin = username;
                        break;
                    case "1" : complaint_admin = hostel +"_admin";
                        break;
                    case "2" :
                        String insti_type = "maintainance";
                        complaint_admin = insti_type +"_admin";
                        break;
                }

                String titlequery = ""; String descriptionquery = ""; String adminquery = "";
                try {
                    titlequery = URLEncoder.encode(title, "utf-8");
                    descriptionquery = URLEncoder.encode(description, "utf-8");
                    adminquery = URLEncoder.encode(complaint_admin, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String addcomplaints_url = "http://"+LoginActivity.ip+"/User/addcomplaint/title"+titlequery+"/description/"+descriptionquery+"/type/"+complaint_type+"/postedby/"+username+"/admin/"+adminquery;
                Log.d(TAG, addcomplaints_url);

                //this is entered when the user has just logged in...
                RequestQueue addComplaintRequestQueue = Volley.newRequestQueue(getContext(), SessionManager.httpStack);
                //requestqueue is made using http-stack as we need to check the sessions of the logged in user
                JsonObjectRequest addComplaintRequest = new JsonObjectRequest
                        (Request.Method.GET, addcomplaints_url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(TAG, response.toString());
                                Toast.makeText(getContext(), "Added complaint", LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                VolleyLog.d(TAG, "Error: " + error.getMessage());
                                //pDialog.setMessage(error.getMessage());
                                Toast.makeText(getContext(), "Failed to add complaint", LENGTH_LONG).show();
                                //pDialog.setMessage(error.getCause().toString());
                                //pDialog.hide();
                            }
                        });
                addComplaintRequestQueue.add(addComplaintRequest);

            }
        });

    }
}
