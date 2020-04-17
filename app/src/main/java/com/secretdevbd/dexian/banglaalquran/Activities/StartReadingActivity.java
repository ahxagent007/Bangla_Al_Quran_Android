package com.secretdevbd.dexian.banglaalquran.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.secretdevbd.dexian.banglaalquran.DB.ARABIC;
import com.secretdevbd.dexian.banglaalquran.DB.BANGLA;
import com.secretdevbd.dexian.banglaalquran.DB.DatabaseHandler;
import com.secretdevbd.dexian.banglaalquran.DB.NAMES;
import com.secretdevbd.dexian.banglaalquran.DB.PRO;
import com.secretdevbd.dexian.banglaalquran.DB.SURAA;
import com.secretdevbd.dexian.banglaalquran.DB.Sura;
import com.secretdevbd.dexian.banglaalquran.R;
import com.secretdevbd.dexian.banglaalquran.SharedPreffClass;

import java.util.ArrayList;

public class StartReadingActivity extends AppCompatActivity {

    String TAG = "XIAN";

    Spinner SP_sura;
    RecyclerView RV_ayats;
    Button btn_next;

    DatabaseHandler DB;

    int CURRENT_SURA;
    boolean appStart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_reading);

        SP_sura = findViewById(R.id.SP_sura);
        RV_ayats = findViewById(R.id.RV_ayats);
        btn_next = findViewById(R.id.btn_next);

        DB = new DatabaseHandler(getApplicationContext());

        ArrayList<String> suraList = new ArrayList<String>();

        ArrayList<NAMES> names = DB.getAllNames();

        for(NAMES n : names){
            suraList.add(n.getText());
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, suraList);
        SP_sura.setAdapter(spinnerArrayAdapter);

        CURRENT_SURA = new SharedPreffClass(getApplicationContext()).getCurrentSura();
        Log.i(TAG,"CURRENT SURA : "+CURRENT_SURA);

        SP_sura.setSelection(CURRENT_SURA - 1);

        SURAA sura = DB.getSURAByno(CURRENT_SURA);

        /*ArrayList<ARABIC> arabics = sura.getArabics();
        ArrayList<BANGLA> banglas = sura.getBanglas();
        ArrayList<PRO> pros = sura.getPros();*/

        ArrayList<String> arabics = sura.getARABIC();
        ArrayList<String> banglas = sura.getBANGLA();
        ArrayList<String> pros = sura.getPRO();

        /*ArrayList<ARABIC> arabics = DB.getAllArabicBySura(CURRENT_SURA);
        ArrayList<BANGLA> banglas = DB.getAllBanglaBySura(CURRENT_SURA);
        ArrayList<PRO> pros = DB.getAllPronunciationBySura(CURRENT_SURA);*/

        Log.i(TAG, "SIZE : "+arabics.size());

        //RecyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RV_ayats.setLayoutManager(mLayoutManager);

        RecyclerView.Adapter mRecycleAdapter = new RecycleViewAdapterForAyats(getApplicationContext(), arabics, banglas, pros);
        RV_ayats.setAdapter(mRecycleAdapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RV_ayats.smoothScrollToPosition(new SharedPreffClass(getApplicationContext()).getCurrentAya());
                Log.i(TAG,"CURRENT AYA : "+new SharedPreffClass(getApplicationContext()).getCurrentAya());
                appStart = false;
            }
        }, 200);


        RV_ayats.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    int position = getCurrentItem();
                    Log.i(TAG,"position : "+position);
                    new SharedPreffClass(getApplicationContext()).setCurrentAya(position);
                    //recyclerView.getLayoutManager().scrollToPosition(position);

                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //Log.i(TAG, "dx : "+dx+ " dy : "+dy);
            }


        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(CURRENT_SURA >= 114){
                    return;
                }
                CURRENT_SURA++;
                new SharedPreffClass(getApplicationContext()).setCurrentSura(CURRENT_SURA);
                new SharedPreffClass(getApplicationContext()).setCurrentAya(0);


                SP_sura.setSelection(CURRENT_SURA - 1);

                SURAA sura = DB.getSURAByno(CURRENT_SURA);

                ArrayList<String> arabics = sura.getARABIC();
                ArrayList<String> banglas = sura.getBANGLA();
                ArrayList<String> pros = sura.getPRO();
                /*ArrayList<ARABIC> arabics = sura.getArabics();
                ArrayList<BANGLA> banglas = sura.getBanglas();
                ArrayList<PRO> pros = sura.getPros();*/

                /*ArrayList<ARABIC> arabics = DB.getAllArabicBySura(CURRENT_SURA);
                ArrayList<BANGLA> banglas = DB.getAllBanglaBySura(CURRENT_SURA);
                ArrayList<PRO> pros = DB.getAllPronunciationBySura(CURRENT_SURA);*/

                //RecyclerView
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                RV_ayats.setLayoutManager(mLayoutManager);

                RecyclerView.Adapter mRecycleAdapter = new RecycleViewAdapterForAyats(getApplicationContext(), arabics, banglas, pros);
                RV_ayats.setAdapter(mRecycleAdapter);
            }
        });

        SP_sura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CURRENT_SURA = i+1;
                new SharedPreffClass(getApplicationContext()).setCurrentSura(CURRENT_SURA);
                if(!appStart){
                    new SharedPreffClass(getApplicationContext()).setCurrentAya(0);

                }
                //
                //Log.i(TAG, "SP_sura.setOnItemSelected");
                SURAA sura = DB.getSURAByno(CURRENT_SURA);

                ArrayList<String> arabics = sura.getARABIC();
                ArrayList<String> banglas = sura.getBANGLA();
                ArrayList<String> pros = sura.getPRO();
                /*ArrayList<ARABIC> arabics = sura.getArabics();
                ArrayList<BANGLA> banglas = sura.getBanglas();
                ArrayList<PRO> pros = sura.getPros();*/

                /*ArrayList<ARABIC> arabics = DB.getAllArabicBySura(CURRENT_SURA);
                ArrayList<BANGLA> banglas = DB.getAllBanglaBySura(CURRENT_SURA);
                ArrayList<PRO> pros = DB.getAllPronunciationBySura(CURRENT_SURA);*/

                //RecyclerView
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                RV_ayats.setLayoutManager(mLayoutManager);

                RecyclerView.Adapter mRecycleAdapter = new RecycleViewAdapterForAyats(getApplicationContext(), arabics, banglas, pros);
                RV_ayats.setAdapter(mRecycleAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private int getCurrentItem(){
        return ((LinearLayoutManager)RV_ayats.getLayoutManager())
                .findFirstVisibleItemPosition();
    }


    public class RecycleViewAdapterForAyats extends RecyclerView.Adapter<RecycleViewAdapterForAyats.ViewHolder> {


        ArrayList<String> arabics;
        ArrayList<String> banglas;
        ArrayList<String> pros;
        Context context;

        public RecycleViewAdapterForAyats(Context context, ArrayList<String> arabics, ArrayList<String> banglas, ArrayList<String> pros) {
            super();
            this.context = context;
            this.arabics = arabics;
            this.banglas = banglas;
            this.pros = pros;

        }

        @Override
        public RecycleViewAdapterForAyats.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.single_aya, viewGroup, false);

            RecycleViewAdapterForAyats.ViewHolder viewHolder = new RecycleViewAdapterForAyats.ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecycleViewAdapterForAyats.ViewHolder viewHolder, final int i) {


            viewHolder.TV_arabic.setText("("+(i+1)+") "+arabics.get(i));
            viewHolder.TV_bangla.setText("("+(i+1)+") "+banglas.get(i));
            viewHolder.TV_pro.setText("("+(i+1)+") "+pros.get(i));


            viewHolder.setClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    if (isLongClick) {

                    } else {

                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return arabics.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

            TextView TV_arabic, TV_pro, TV_bangla;

            private ItemClickListener clickListener;

            public ViewHolder(View itemView) {
                super(itemView);

                TV_arabic = itemView.findViewById(R.id.TV_arabic);
                TV_pro = itemView.findViewById(R.id.TV_pro);
                TV_bangla = itemView.findViewById(R.id.TV_bangla);

                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);
            }

            public void setClickListener(ItemClickListener itemClickListener) {
                this.clickListener = itemClickListener;
            }

            @Override
            public void onClick(View view) {
                clickListener.onClick(view, getPosition(), false);
            }

            @Override
            public boolean onLongClick(View view) {
                clickListener.onClick(view, getPosition(), true);
                return true;
            }
        }

    }
}
