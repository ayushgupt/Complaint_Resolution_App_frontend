package com.example.quantumcoder.iitd_complaints;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;
import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Admin on 26-Mar-16.
 */
public class Fragment_hostel_unresolved extends android.support.v4.app.Fragment{

    private static int SPLASH_TIME_OUT =500 ;


    String head[] = {"Lanban", "Dogs ki  bakwas", "Library Timings"};

    String owner[] = {"Ayush", "Aniket", "Manish"};

    String time[]= {"4 days ago", "5 days ago ", "100 days ago"    };

    String upvote[]= {"200 Upvotes","5500 Upvotes","900 Upvotes"};

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("ListFragment", "onActivityCreated().");
        Log.v("ListsavedInstanceState", savedInstanceState == null ? "true" : "false");

        //Generate list View from ArrayList
        //displayListView();

        Institute_ArrayAdapter dataAdapter = new Institute_ArrayAdapter(getContext(), head, owner,time ,upvote );
        ListView listView = (ListView) getView().findViewById(R.id.complaints);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        //enables filtering for the contents of the given ListView
        listView.setTextFilterEnabled(true);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Toast.makeText(getActivity().getApplicationContext(), "Assignment selected", LENGTH_SHORT).show();
                Button LoginButton = (Button) view.findViewById(R.id.details);

//                MainActivity.selectedassignment = Integer.parseInt((String) textview.getText());
//                Log.d(TAG, String.valueOf(MainActivity.selectedassignment));

                /*
                FragmentTransaction xfragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                xfragmentTransaction.replace(R.id.containerView, new FragmentAssignmentDisplay()).commit();
                */
                LoginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Start Main Activity
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);

                    }
                });
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // This method will be executed once the timer is over
//                        // Start your app main activity
//                        Intent i = new Intent(getActivity(), detail_comp_activity.class);
//                        startActivity(i);
//                        // overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//
//                        getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
//                        // close this activity
//                        //getActivity().finish();
//                    }
//                }, SPLASH_TIME_OUT);
            }


        });



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
        return inflater.inflate(R.layout.hostel_unresolved,null);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Button LoginButton = (Button) getView().findViewById(R.id.details);
//        LoginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start Main Activity
//                Intent i = new Intent(getActivity().getApplicationContext(), detail_comp_activity.class);
//                startActivity(i);
//
//            }
//        });

    }
}
