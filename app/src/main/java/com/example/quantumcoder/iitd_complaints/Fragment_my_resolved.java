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
public class Fragment_my_resolved extends android.support.v4.app.Fragment{


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("MyResFragment", "onActivityCreated().");
        Log.v("ListsavedInstanceState", savedInstanceState == null ? "true" : "false");

        String username = null; String hostel = null;
        try {
            username = (String) SessionManager.getUserData().get("kerberos_username");
            //hostel = (String) SessionManager.getUserData().get("hostel");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String my_resolved_url = ("http://"+LoginActivity.ip + "/User/usercomplaints/username/"+username+"/resolved/1/startindex/0/endindex/10").trim();
        Log.d(TAG, my_resolved_url);


        RequestQueue myResRequestQueue = Volley.newRequestQueue(getContext(), SessionManager.httpStack);
        //requestqueue is made using http-stack as we need to check the sessions of the logged in user
        JsonObjectRequest myResRequest = new JsonObjectRequest
                (Request.Method.GET, my_resolved_url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        SessionManager.my_resolved_complaints = response;
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
        myResRequestQueue.add(myResRequest);

    }


    private void displayListView()
    {
        JSONArray complaints_array = null;
        try {
            complaints_array = (JSONArray) SessionManager.my_resolved_complaints.get("complaints");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String head[] = new String[complaints_array.length()] ;
        String owner[] = new String[complaints_array.length()] ;
        String time[] = new String[complaints_array.length()] ;
        String upvote[] = new String[complaints_array.length()] ;

        for(int i=0;i<complaints_array.length();i++){
            try {
                head[i] = complaints_array.getJSONObject(i).getString("title");
                owner[i] = complaints_array.getJSONObject(i).getString("postedby");
                time[i] = complaints_array.getJSONObject(i).getString("datetime_created");
                upvote[i] = complaints_array.getJSONObject(i).getString("upvotes_num")+" upvotes";
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        //create an ArrayAdaptar from the String Array
        Institute_ArrayAdapter dataAdapter = new Institute_ArrayAdapter(getContext(), head, owner,time ,upvote );
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
        return inflater.inflate(R.layout.complaint_details,null);
    }
}
