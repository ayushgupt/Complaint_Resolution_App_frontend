package com.example.quantumcoder.iitd_complaints;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Admin on 26-Mar-16.
 */
public class Fragment_my_resolved extends android.support.v4.app.Fragment{


    String head[] = {"Lanban", "Dogs ki  bakwas", "Library Timings"};

    String time[]= {"4 days ago", "5 days ago ", "100 days ago"    };

    String upvote[]= {"200 Upvotes","5500 Upvotes","900 Upvotes"};

    String type[]={"Hostel","Institute","Individual"};

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("ListFragment", "onActivityCreated().");
        Log.v("ListsavedInstanceState", savedInstanceState == null ? "true" : "false");

        My_resolved_ArrayAdapter dataAdapter = new My_resolved_ArrayAdapter(getContext(), head ,time ,upvote,type );
        ListView listView = (ListView) getView().findViewById(R.id.complaints);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        //enables filtering for the contents of the given ListView
        listView.setTextFilterEnabled(true);

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
        return inflater.inflate(R.layout.complaint_details,null);
    }
}
