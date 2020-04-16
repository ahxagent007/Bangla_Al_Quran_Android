package com.secretdevbd.dexian.banglaalquran.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.secretdevbd.dexian.banglaalquran.DB.DatabaseHandler;
import com.secretdevbd.dexian.banglaalquran.R;

import java.util.ArrayList;

public class StartReadingActivity extends AppCompatActivity {

    Spinner SP_sura;
    RecyclerView RV_ayats;

    DatabaseHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_reading);

        SP_sura = findViewById(R.id.SP_sura);
        RV_ayats = findViewById(R.id.RV_ayats);

        DB = new DatabaseHandler(getApplicationContext());

        ArrayList<String> suraList = new ArrayList<String>();

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, suraList);
        SP_sura.setAdapter(spinnerArrayAdapter);
    }
}
