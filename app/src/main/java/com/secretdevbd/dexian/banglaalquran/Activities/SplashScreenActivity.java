package com.secretdevbd.dexian.banglaalquran.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.secretdevbd.dexian.banglaalquran.DB.DatabaseHandler;
import com.secretdevbd.dexian.banglaalquran.DB.ResponseDB;
import com.secretdevbd.dexian.banglaalquran.R;
import com.secretdevbd.dexian.banglaalquran.SharedPreffClass;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SplashScreenActivity extends AppCompatActivity {

    String TAG = "XIAN";

    ProgressBar PB_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        PB_loading = findViewById(R.id.PB_loading);
        PB_loading.setVisibility(View.VISIBLE);

        jsonRequest();


    }

    private void simpleRequestForAllData() {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        final Gson g = new Gson();

        String url = "https://quran.secretdevbd.com/Quran"; //http://127.0.0.1:5000/API/QURAN
        Log.i(TAG, "URL : " + url);
        // Request a string response from the provided URL.
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.i(TAG, response);

                        ResponseDB responseDB = g.fromJson(response, ResponseDB.class);

                        DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());

                        databaseHandler.addAllArabic(responseDB.getData().getARABIC());
                        databaseHandler.addAllAudio(responseDB.getData().getAUDIO());
                        databaseHandler.addAllBangla(responseDB.getData().getBANGLA());
                        databaseHandler.addAllNames(responseDB.getData().getNAMES());
                        databaseHandler.addAllPronunciation(responseDB.getData().getPRO());

                        PB_loading.setVisibility(View.INVISIBLE);

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();


                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.i(TAG, "simpleRequestForAllData : "+error);
                        Log.i(TAG, ""+error.networkResponse.statusCode);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("version", "1");

                return params;
            }
        };


        // Add the request to the RequestQueue.
        //queue.add(stringRequest);
        queue.add(postRequest);
    }

    private void jsonRequest(){

        String URL = "https://quran.secretdevbd.com/Quran";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        Map<String, String> jsonParams = new HashMap<String, String>();

        jsonParams.put("version",new SharedPreffClass(getApplicationContext()).getDLstatus());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String msg = response.toString();

                        Log.i(TAG,"Response : "+msg);

                        Gson g = new Gson();
                        ResponseDB responseDB = g.fromJson(msg, ResponseDB.class);

                        DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());

                        databaseHandler.addAllArabic(responseDB.getData().getARABIC());
                        databaseHandler.addAllAudio(responseDB.getData().getAUDIO());
                        databaseHandler.addAllBangla(responseDB.getData().getBANGLA());
                        databaseHandler.addAllNames(responseDB.getData().getNAMES());
                        databaseHandler.addAllPronunciation(responseDB.getData().getPRO());

                        PB_loading.setVisibility(View.INVISIBLE);

                        new SharedPreffClass(getApplicationContext()).setDLstatus("1");

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG,"jsonRequest ERROR :"+error.toString());
                    }
                }
        );


        requestQueue.add(jsonObjectRequest);
    }
}
