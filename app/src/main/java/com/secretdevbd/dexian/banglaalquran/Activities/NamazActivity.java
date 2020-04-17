package com.secretdevbd.dexian.banglaalquran.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.secretdevbd.dexian.banglaalquran.DB.DatabaseHandler;
import com.secretdevbd.dexian.banglaalquran.DB.ResponseDB;
import com.secretdevbd.dexian.banglaalquran.DB.ResponseNamazTime;
import com.secretdevbd.dexian.banglaalquran.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NamazActivity extends AppCompatActivity {

    String TAG = "XIAN";

    double latitude = 23.789168;
    double longitude = 90.399153;

    ProgressBar PB_loadingNamaztime;
    TextView TV_enDate, TV_hijriDate, TV_time_zone, TV_Fajr, TV_Duhr, TV_Asr, TV_Maghrib, TV_Isha, TV_Imsak, TV_Sunrise, TV_Sunset, TV_Midnight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namaz);

        //http://api.aladhan.com/v1/timings/1398332113?latitude=23.727176&longitude=90.380313

        PB_loadingNamaztime = findViewById(R.id.PB_loadingNamaztime);
        TV_enDate = findViewById(R.id.TV_enDate);
        TV_hijriDate = findViewById(R.id.TV_hijriDate);
        TV_time_zone = findViewById(R.id.TV_time_zone);
        TV_Fajr = findViewById(R.id.TV_Fajr);
        TV_Duhr = findViewById(R.id.TV_Duhr);
        TV_Asr = findViewById(R.id.TV_Asr);
        TV_Maghrib = findViewById(R.id.TV_Maghrib);
        TV_Isha = findViewById(R.id.TV_Isha);
        TV_Imsak = findViewById(R.id.TV_Imsak);
        TV_Sunrise = findViewById(R.id.TV_Sunrise);
        TV_Sunset = findViewById(R.id.TV_Sunset);
        TV_Midnight = findViewById(R.id.TV_Midnight);

        if(checkPhonePermission()){
            generateLocation();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    simpleRequestForNamazTime();
                }
            }, 200);

        }else {
            finish();
        }
    }

    private void simpleRequestForNamazTime() {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        final Gson g = new Gson();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String dddd = dateFormat.format(date);

        String url = "http://api.aladhan.com/v1/timings/"+dddd+"?latitude="+latitude+"&longitude="+longitude+"&method=2";
        Log.i(TAG, "URL : " + url);
        // Request a string response from the provided URL.
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.i(TAG, response);

                        final ResponseNamazTime responseNamazTime = new Gson().fromJson(response, ResponseNamazTime.class);

                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                PB_loadingNamaztime.setVisibility(View.GONE);

                                SimpleDateFormat Format24 = new SimpleDateFormat("HH:mm");
                                SimpleDateFormat Format12 = new SimpleDateFormat("hh:mm a");


                                TV_enDate.setText(responseNamazTime.getData().getDate().getReadable()+ " ( " +responseNamazTime.getData().getDate().getHijri().getWeekday().getEn()+" )");
                                TV_hijriDate.setText(responseNamazTime.getData().getDate().getHijri().getDate());
                                TV_time_zone.setText(responseNamazTime.getData().getMeta().getTimezone());

                                try {
                                    TV_Fajr.setText(Format12.format(Format24.parse(responseNamazTime.getData().getTimings().getFajr())).toUpperCase());
                                    TV_Duhr.setText(Format12.format(Format24.parse(responseNamazTime.getData().getTimings().getDhuhr())).toUpperCase());
                                    TV_Asr.setText(Format12.format(Format24.parse(responseNamazTime.getData().getTimings().getAsr())).toUpperCase());
                                    TV_Maghrib.setText(Format12.format(Format24.parse(responseNamazTime.getData().getTimings().getMaghrib())).toUpperCase());
                                    TV_Isha.setText(Format12.format(Format24.parse(responseNamazTime.getData().getTimings().getIsha())).toUpperCase());
                                    TV_Imsak.setText(Format12.format(Format24.parse(responseNamazTime.getData().getTimings().getImsak())).toUpperCase());
                                    TV_Sunrise.setText(Format12.format(Format24.parse(responseNamazTime.getData().getTimings().getSunrise())).toUpperCase());
                                    TV_Sunset.setText(Format12.format(Format24.parse(responseNamazTime.getData().getTimings().getSunset())).toUpperCase());
                                    TV_Midnight.setText(Format12.format(Format24.parse(responseNamazTime.getData().getTimings().getMidnight())).toUpperCase());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }


                            }
                        });


                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.i(TAG, "simpleRequestForNamazTime : "+error);
                        Toast.makeText(getApplicationContext(),"No Internet", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("latitude", ""+latitude);
                params.put("longitude", ""+longitude);
                params.put("method", "2");

                return params;
            }
        };


        // Add the request to the RequestQueue.
        //queue.add(stringRequest);
        queue.add(postRequest);
    }

    int PERMISSIONS_REQUEST_LOC = 99;
    private boolean checkPhonePermission() {

        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                Log.i(TAG, "NO LOCATION PERMISSION");

                new AlertDialog.Builder(this)
                        .setTitle("Location Permission")
                        .setMessage("We need your permission to run the app")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown

                                ActivityCompat.requestPermissions(NamazActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_LOC);
                            }
                        })
                        .create()
                        .show();


            }else {
                Log.i(TAG, "ALL PERMISSION");
                //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                //finish();
                return true;
            }

        } else {
            Log.i(TAG, "Build.VERSION.SDK_INT < 23");

            int permissionLocation = PermissionChecker.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);

            if (permissionLocation != PermissionChecker.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(NamazActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_LOC);
            } else {
                Log.i(TAG, "ALL PERMISSION OK");

                return true;
            }
        }

        return false;
    }

    private void generateLocation(){
       checkPhonePermission();

        LocationManager lm = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Log.i(TAG, "LOCATION : "+latitude+","+longitude);
        }

        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1000, locationListener);
    }

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Log.i(TAG, "LOCATION : "+latitude+","+longitude);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
}
