package com.secretdevbd.dexian.banglaalquran.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.secretdevbd.dexian.banglaalquran.R;

public class MainActivity extends AppCompatActivity {

    Button btn_startReading, btn_namazTime, btn_website, btn_helpus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_startReading = findViewById(R.id.btn_startReading);
        btn_namazTime = findViewById(R.id.btn_namazTime);
        btn_website = findViewById(R.id.btn_website);
        btn_helpus = findViewById(R.id.btn_helpus);

        btn_startReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),StartReadingActivity.class));
            }
        });

        btn_namazTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),NamazActivity.class));
            }
        });
        btn_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WEBSITE("http://antor.kitsware.com/quran/");
            }
        });
        btn_helpus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WEBSITE("https://www.secretdevbd.com/Xian");
            }
        });

    }

    private void WEBSITE(String URL){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        startActivity(browserIntent);
    }
}
