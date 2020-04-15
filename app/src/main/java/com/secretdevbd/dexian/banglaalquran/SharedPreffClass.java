package com.secretdevbd.dexian.banglaalquran;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreffClass {

    Context context;

    public SharedPreffClass(Context context) {
        this.context = context;
    }


    public void saveIPAddress(String ip){
        SharedPreferences mSharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("IP_ADDRESS", ip);

        mEditor.apply();
    }


    public String getIPAddress() {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        String s = mSharedPreferences.getString("IP_ADDRESS", "-1");

        return s;
    }


    public void savePhoneNumber(String P) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("PHONE", P);

        mEditor.apply();
    }

    public String getPhoneNumber() {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        String s = mSharedPreferences.getString("PHONE", "0");

        return s;
    }


    public void saveSimSerial(String P) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("SIM_SERIAL", P);

        mEditor.apply();
    }


    public String getSimSerial() {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        String s = mSharedPreferences.getString("SIM_SERIAL", "-1");

        return s;
    }


    public void saveUserName(String P) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("USER_NAME", P);

        mEditor.apply();
    }


    public String getUserName() {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        String s = mSharedPreferences.getString("USER_NAME", "Null");

        return s;
    }

    public void saveUserPic(String P) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("USER_PIC", P);

        mEditor.apply();
    }


    public String getUserPic() {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        String s = mSharedPreferences.getString("USER_PIC", "Null");

        return s;
    }





}
