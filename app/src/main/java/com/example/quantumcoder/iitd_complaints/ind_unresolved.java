package com.example.quantumcoder.iitd_complaints;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.LENGTH_SHORT;
import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Admin on 26-Mar-16.
 */
public class ind_unresolved extends android.support.v4.app.Fragment{



    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("IndiUnresFragment", "onActivityCreated().");
        Log.v("ListsavedInstanceState", savedInstanceState == null ? "true" : "false");

        String username = null; String hostel = null;
        try {
            username = (String) SessionManager.getUserData().get("kerberos_username");
            //hostel = (String) SessionManager.getUserData().get("hostel");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String indi_unresolved_url = ("http://"+LoginActivity.ip + "/User/listcomplaints/username/"+username+"/type/0/resolved/0/startindex/0/endindex/10").trim();
        Log.d(TAG, indi_unresolved_url);


        RequestQueue indiUnresRequestQueue = Volley.newRequestQueue(getContext(), SessionManager.httpStack);
        //requestqueue is made using http-stack as we need to check the sessions of the logged in user
        JsonObjectRequest indiUnresRequest = new JsonObjectRequest
                (Request.Method.GET, indi_unresolved_url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        SessionManager.indi_unresolved_complaints = response;
                        displayListView();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        VolleyLog.v(TAG, "Error: " + error.getMessage());
                        //pDialog.setMessage(error.getMessage());
                        Toast.makeText(getContext(), "Failed to fetch complaints data", Toast.LENGTH_LONG).show();
                        //pDialog.setMessage(error.getCause().toString());
                        //pDialog.hide();
                    }
                });
        indiUnresRequestQueue.add(indiUnresRequest);

    }


    private void displayListView()
    {
        JSONArray complaints_array = null;
        try {
            complaints_array = (JSONArray) SessionManager.indi_unresolved_complaints.get("complaints");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String head[] = new String[complaints_array.length()] ;
        String time[] = new String[complaints_array.length()] ;

        for(int i=0;i<complaints_array.length();i++){
            try {
                head[i] = complaints_array.getJSONObject(i).getString("title");
                time[i] = complaints_array.getJSONObject(i).getString("datetime_created");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        //create an ArrayAdaptar from the String Array
        ind_unresolved_ArrayAdapter dataAdapter = new ind_unresolved_ArrayAdapter(getContext(), head ,time  );
        ListView listView = (ListView) getView().findViewById(R.id.complaints);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        //enables filtering for the contents of the given ListView
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "Complaint selected", LENGTH_SHORT).show();

                // Send the URL to the host activity
                //    mListener.onURLSelected(((TextView) view).getText().toString());

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ind_unresolved,null);
    }
}
