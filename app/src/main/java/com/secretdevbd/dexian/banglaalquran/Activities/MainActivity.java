package com.secretdevbd.dexian.banglaalquran.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.secretdevbd.dexian.banglaalquran.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void WEBSITE(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://antor.kitsware.com/quran/"));
        startActivity(browserIntent);
    }
}
