package com.example.quantumcoder.iitd_complaints;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class FragmentNotifications extends Fragment {

    private static final String TAG = "quantumcoder";

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("FragmentNotifications", "onActivityCreated().");
        Log.v("ListsavedInstanceState", savedInstanceState == null ? "true" : "false");

        String notifications_url = ("http://"+LoginActivity.ip + "/notifications/startindex/1/endindex/10").trim();
        Log.d(TAG, notifications_url);


         RequestQueue notifsRequestQueue = Volley.newRequestQueue(getContext(), SessionManager.httpStack);
        //requestqueue is made using http-stack as we need to check the sessions of the logged in user
         JsonObjectRequest notifsRequest = new JsonObjectRequest
                (Request.Method.GET, notifications_url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        SessionManager.notifications = response;
                        displayListView();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        //pDialog.setMessage(error.getMessage());
                        Toast.makeText(getContext(), "Failed to fetch notifications data", Toast.LENGTH_LONG).show();
                        //pDialog.setMessage(error.getCause().toString());
                        //pDialog.hide();
                    }
                });
        notifsRequestQueue.add(notifsRequest);



        //Generate list View from ArrayList
        //displayListView();


    }

    private void displayListView() {

    }


//    private void displayListView() {
//
//        JSONArray notifications_array = null;
//        try {
//            notifications_array = (JSONArray) SessionManager.notifications.get("notifications");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        String t[] = new String[notifications_array.length()] ;
//        String m[] = new String[notifications_array.length()] ;
//
//        for(int i=0;i<notifications_array.length();i++){
//            try {
//                t[i] = notifications_array.getJSONObject(i).getString("description");
//                m[i] = notifications_array.getJSONObject(i).getString("created_at");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        //create an ArrayAdaptar from the String Array
//        NotifArrayAdapter dataAdapter = new NotifArrayAdapter(getContext(), t, m);
//        ListView listView = (ListView) getView().findViewById(R.id.notification);
//        // Assign adapter to ListView
//        listView.setAdapter(dataAdapter);
//        //enables filtering for the contents of the given ListView
//        listView.setTextFilterEnabled(true);
//
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                Toast.makeText(getActivity().getApplicationContext(), "Notification Selected", LENGTH_SHORT).show();
//
//                // Send the URL to the host activity
//                //    mListener.onURLSelected(((TextView) view).getText().toString());
//
//            }
//        });
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(com.example.quantumcoder.iitd_complaints.R.layout.notification_list,null);
    }
}
