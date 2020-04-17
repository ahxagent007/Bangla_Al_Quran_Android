package com.secretdevbd.dexian.banglaalquran.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import com.secretdevbd.dexian.banglaalquran.DB.ResponseDBBB;
import com.secretdevbd.dexian.banglaalquran.DB.SURAA;
import com.secretdevbd.dexian.banglaalquran.DB.Sura;
import com.secretdevbd.dexian.banglaalquran.R;
import com.secretdevbd.dexian.banglaalquran.SharedPreffClass;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    String TAG = "XIAN";

    ProgressBar PB_loading, PB_loadingBar;

    Timer t;
    int prog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        PB_loading = findViewById(R.id.PB_loading);
        PB_loadingBar = findViewById(R.id.PB_loadingBar);
        PB_loading.setVisibility(View.VISIBLE);
        PB_loadingBar.setVisibility(View.VISIBLE);

        screenOn();
        jsonRequest();

         prog = 0;

        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        PB_loadingBar.setProgress(prog);
                        prog++;
                        Log.i(TAG, "Prog : "+prog);
                    }
                });

            }
        }, 0, 2500);


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

                        /*databaseHandler.addAllArabic(responseDB.getData().getARABIC());
                        databaseHandler.addAllAudio(responseDB.getData().getAUDIO());
                        databaseHandler.addAllBangla(responseDB.getData().getBANGLA());
                        databaseHandler.addAllNames(responseDB.getData().getNAMES());
                        databaseHandler.addAllPronunciation(responseDB.getData().getPRO());*/

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

        String URL = "https://quran.secretdevbd.com/al_quran_json";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        Map<String, String> jsonParams = new HashMap<String, String>();

        jsonParams.put("version",new SharedPreffClass(getApplicationContext()).getDLstatus());
        //jsonParams.put("version",""+0);

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
                        final ResponseDBBB responseDBBB = g.fromJson(msg, ResponseDBBB.class);


                        if(responseDBBB.getStatus() == 1){

                            Log.i(TAG, responseDBBB.getData().getSURA().size()+" |");

                            final ArrayList<SURAA> suras = responseDBBB.getData().getSURA();

                            final DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());

                            new Thread(new Runnable() {
                                public void run() {
                                    databaseHandler.addAllSURA2(suras);
                                }
                            }).start();

                            new Thread(new Runnable() {
                                public void run() {
                                    // a potentially time consuming task
                                    databaseHandler.addAllNames(responseDBBB.getData().getNAMES());
                                }
                            }).start();


                            new SharedPreffClass(getApplicationContext()).setDLstatus(""+responseDBBB.getStatus());

                            new Handler().postDelayed(new Runnable(){
                                @Override
                                public void run() {
                                    PB_loadingBar.setProgress(100);
                                    PB_loading.setVisibility(View.INVISIBLE);
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    finish();
                                }
                            }, 10000);


                            /*new Thread(new Runnable() {
                                public void run() {
                                    // a potentially time consuming task
                                    databaseHandler.addAllBangla(responseDB.getData().getBANGLA());
                                }
                            }).start();
                            new Thread(new Runnable() {
                                public void run() {
                                    // a potentially time consuming task
                                    databaseHandler.addAllArabic(responseDB.getData().getARABIC());
                                }
                            }).start();

                            new Thread(new Runnable() {
                                public void run() {
                                    // a potentially time consuming task
                                    databaseHandler.addAllAudio(responseDB.getData().getAUDIO());
                                }
                            }).start();
                            new Thread(new Runnable() {
                                public void run() {
                                    // a potentially time consuming task
                                    databaseHandler.addAllNames(responseDB.getData().getNAMES());
                                }
                            }).start();
                            new Thread(new Runnable() {
                                public void run() {
                                    // a potentially time consuming task
                                    databaseHandler.addAllPronunciation(responseDB.getData().getPRO());

                                }
                            }).start();

                            new SharedPreffClass(getApplicationContext()).setDLstatus(""+responseDB.getStatus());

                            new Handler().postDelayed(new Runnable(){
                                @Override
                                public void run() {
                                    PB_loadingBar.setProgress(100);
                                    PB_loading.setVisibility(View.INVISIBLE);
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    finish();
                                }
                            }, 200000);*/

                        }else{
                            PB_loadingBar.setProgress(100);
                            PB_loading.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG,"jsonRequest ERROR :"+error.toString());
                        if(new SharedPreffClass(getApplicationContext()).getDLstatus().equalsIgnoreCase("1")){
                            PB_loadingBar.setProgress(100);
                            PB_loading.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }else{
                            restartActivity(SplashScreenActivity.this);

                        }
                    }
                }
        );


        requestQueue.add(jsonObjectRequest);
    }

    public static void restartActivity(Activity activity){
        if (Build.VERSION.SDK_INT >= 11) {
            activity.recreate();
        } else {
            activity.finish();
            activity.startActivity(activity.getIntent());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        t.cancel();
    }

    protected PowerManager.WakeLock mWakeLock;
    private void screenOn(){

        final Window window = this.getWindow();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
                window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
                window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
            }
        });


        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "MyApp::MyWakelockTag");
        this.mWakeLock.acquire();
    }


    private void screenOFF(){
        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyApp::MyWakelockTag");
        this.mWakeLock.acquire();
    }
}
