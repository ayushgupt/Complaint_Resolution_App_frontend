package com.example.quantumcoder.iitd_complaints;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentProfile extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(com.example.quantumcoder.iitd_complaints.R.layout.profile_layout,null);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        JSONObject userdata = SessionManager.getUserData();
//
//        EditText firstnametext = (EditText) view.findViewById(R.id.first_name);
//        try {
//            firstnametext.setText(userdata.getString("first_name"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        EditText lastnametext = (EditText) view.findViewById(R.id.last_name);
//        try {
//            lastnametext.setText(userdata.getString("last_name"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        EditText emailtext = (EditText) view.findViewById(R.id.Email);
//        try {
//            emailtext.setText(userdata.getString("email"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        EditText usernametext = (EditText) view.findViewById(R.id.Username);
//        try {
//            usernametext.setText(userdata.getString("username"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        EditText entrynumbertext = (EditText) view.findViewById(R.id.EntryNumber);
//        try {
//            entrynumbertext.setText(userdata.getString("entry_no"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        EditText typetext = (EditText) view.findViewById(R.id.type);
//        try {
//            typetext.setText(userdata.getString("type"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
}
