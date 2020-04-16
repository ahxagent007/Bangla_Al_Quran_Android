package com.secretdevbd.dexian.banglaalquran;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreffClass {

    Context context;

    public SharedPreffClass(Context context) {
        this.context = context;
    }


    public void setDLstatus(String dl){
        SharedPreferences mSharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("DL_STATUS", dl);

        mEditor.apply();
    }


    public String getDLstatus() {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        String s = mSharedPreferences.getString("DL_STATUS", "0");

        return s;
    }


    public void setCurrentSura(int s){
        SharedPreferences mSharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt("CURRENT_SURA", s);

        mEditor.apply();
    }


    public int getCurrentSura() {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        int s = mSharedPreferences.getInt("CURRENT_SURA", 1);

        return s;
    }


    public void setCurrentAya(int s){
        SharedPreferences mSharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt("CURRENT_AYA", s);

        mEditor.apply();
    }


    public int getCurrentAya() {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        int s = mSharedPreferences.getInt("CURRENT_AYA", 0);

        return s;
    }








}
