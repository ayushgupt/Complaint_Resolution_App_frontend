package com.example.quantumcoder.iitd_complaints;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class SessionManager {
    // Shared Preferences
    static SharedPreferences pref;

    // Editor for Shared preferences
    static Editor editor;

    // Context
    static Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "LoginPreference";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable private to prevent access from outside)
    public static final String KEY_NAME = "username";

    // Password (make variable private to prevent access from outside)
    public static final String KEY_PASSWORD = "password";

    static HttpStack httpStack;

    // Keys to store user data
    public static final String KEY_USERDATA = "userdata";
    public static final String KEY_COURSEDATA = "coursedata";
    public static final String KEY_GRADES = "grades";

    public static JSONObject notifications;
    public static JSONObject hostel_resolved_complaints;
    public static JSONObject hostel_unresolved_complaints;
    public static JSONObject insti_resolved_complaints;
    public static JSONObject insti_unresolved_complaints;
    public static JSONObject my_resolved_complaints;
    public static JSONObject my_unresolved_complaints;
    public static JSONObject indi_resolved_complaints;
    public static JSONObject indi_unresolved_complaints;





    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();


        DefaultHttpClient httpclient = new DefaultHttpClient();

        CookieStore cookieStore = new BasicCookieStore();
        httpclient.setCookieStore( cookieStore );
        httpStack = new HttpClientStack( httpclient );
    }

    /**
     * Create login session
     * */
    public static void createLoginSession(String name, String password){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PASSWORD, password);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public static void checkLogin(){
        // Check login status
        if(!isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }



    /**
     * Get stored session data
     * */
    public static HashMap<String, String> getLoginDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public static void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        notifications = null;
        hostel_resolved_complaints = null;
        hostel_unresolved_complaints = null;
        insti_resolved_complaints = null;
        insti_unresolved_complaints = null;
        my_resolved_complaints = null;
        my_unresolved_complaints = null;
        indi_resolved_complaints = null;
        indi_unresolved_complaints = null;

        /*
        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
        */

    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public static boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }


    // Manage cookie variables
    public static void setUserData(JSONObject userdata){
        editor.putString(KEY_USERDATA, userdata.toString());
        editor.commit();
    }

    public static JSONObject getUserData(){
        String userdata = pref.getString(KEY_USERDATA,null);
        try {
            JSONObject userdata_json = new JSONObject(userdata);
            return userdata_json;
        } catch (JSONException e) {
            //e.printStackTrace();
            return null;
        }
    }

}
