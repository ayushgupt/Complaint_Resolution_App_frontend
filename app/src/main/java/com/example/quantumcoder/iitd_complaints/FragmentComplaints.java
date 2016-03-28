package com.example.quantumcoder.iitd_complaints;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Admin on 26-Mar-16.
 */
public class FragmentComplaints extends android.support.v4.app.Fragment{
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("ListFragment", "onActivityCreated().");
        Log.v("ListsavedInstanceState", savedInstanceState == null ? "true" : "false");

        //Generate list View from ArrayList
        //displayListView();

    }
//    private void displayListView() {
//
//
//
//        String t[] = {"Week1","Week2","Week3","Week4","Week5","Week6","Week7" +
//                ""} ;
//        String m[]= {"11-1-16","18-1-16","25-1-16","1-2-16","8-2-16","15-2-16","22-2-16"};
//        //create an ArrayAdaptar from the String Array
//        OverviewArrayAdapter dataAdapter = new OverviewArrayAdapter(getContext(), t, m);
//        ListView listView = (ListView) getView().findViewById(R.id.overview_timeline);
//        // Assign adapter to ListView
//        listView.setAdapter(dataAdapter);
//        //enables filtering for the contents of the given ListView
//        listView.setTextFilterEnabled(true);
//
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                Toast.makeText(getActivity().getApplicationContext(), "Week selected", LENGTH_SHORT).show();
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
        return inflater.inflate(com.example.quantumcoder.iitd_complaints.R.layout.list_complaint_1,null);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button LoginButton = (Button) getView().findViewById(com.example.quantumcoder.iitd_complaints.R.id.details);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Main Activity
                Intent i = new Intent(getActivity().getApplicationContext(), detail_comp_activity.class);
                startActivity(i);

            }
        });

    }
}
