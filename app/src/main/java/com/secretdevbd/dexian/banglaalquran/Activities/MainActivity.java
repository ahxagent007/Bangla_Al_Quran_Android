package com.secretdevbd.dexian.banglaalquran.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.secretdevbd.dexian.banglaalquran.R;

public class MainActivity extends AppCompatActivity {

    Button btn_startReading, btn_allSura, btn_namazTime, btn_website, btn_helpus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_startReading = findViewById(R.id.btn_startReading);
        btn_allSura = findViewById(R.id.btn_allSura);
        btn_namazTime = findViewById(R.id.btn_namazTime);
        btn_website = findViewById(R.id.btn_website);
        btn_helpus = findViewById(R.id.btn_helpus);

        btn_startReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),StartReadingActivity.class));
            }
        });
        btn_allSura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_namazTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WEBSITE();
            }
        });
        btn_helpus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void WEBSITE(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://antor.kitsware.com/quran/"));
        startActivity(browserIntent);
    }
}
