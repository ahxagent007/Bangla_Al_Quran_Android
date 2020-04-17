package com.secretdevbd.dexian.banglaalquran.DB;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class DatabaseHandler extends SQLiteOpenHelper {

    String TAG = "XIAN";

    public static final String DATABASE_NAME = "bangla_al_quran.db";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        /*db.execSQL(
                "create table arabic1 (sura integer, aya integer, text text);"
        );
        db.execSQL(
                "create table audio (sura integer, aya integer, text text);"
        );
        db.execSQL(
                "create table bangla1 (sura integer, aya integer, text text);"
        );*/
        db.execSQL(
                "create table names (sura integer, text text);"
        );
        /*db.execSQL(
                "create table pronunciation (sura integer, aya integer, text text);"
        );*/
        db.execSQL(
                "create table SURA (sura_no integer, arabic_json text, bangla_json text, pro_json text, audio_json text);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS arabic1");
        db.execSQL("DROP TABLE IF EXISTS bangla1");
        db.execSQL("DROP TABLE IF EXISTS names");
        db.execSQL("DROP TABLE IF EXISTS pronunciation");
        db.execSQL("DROP TABLE IF EXISTS audio");
        db.execSQL("DROP TABLE IF EXISTS SURA");
        onCreate(db);
    }

    public void deleteArabic() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM arabic1;");
    }
    public void deleteAudio() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM audio;");
    }
    public void deleteBangla() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM bangla1;");
    }
    public void deleteNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM names;");
    }
    public void deletepronunciation() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM pronunciation;");
    }

    public void deleteSURA() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM SURA;");
    }

    public boolean addAllArabic (ArrayList<ARABIC> arabics) {
        this.deleteArabic();

        SQLiteDatabase db = this.getWritableDatabase();
        for(int i=0; i<arabics.size(); i++){

            ContentValues contentValues = new ContentValues();
            contentValues.put("sura", arabics.get(i).getSura());
            contentValues.put("aya", arabics.get(i).getAya());
            contentValues.put("text", arabics.get(i).getText());

            Log.i(TAG, arabics.get(i).toString());

            db.insert("arabic1", null, contentValues);
        }

        return true;
    }
    public boolean addAllBangla (ArrayList<BANGLA> banglas) {
        this.deleteBangla();

        SQLiteDatabase db = this.getWritableDatabase();
        for(int i=0; i<banglas.size(); i++){

            ContentValues contentValues = new ContentValues();
            contentValues.put("sura", banglas.get(i).getSura());
            contentValues.put("aya", banglas.get(i).getAya());
            contentValues.put("text", banglas.get(i).getText());

            Log.i(TAG, banglas.get(i).toString());

            db.insert("bangla1", null, contentValues);
        }

        return true;
    }
    public boolean addAllAudio (ArrayList<AUDIO> audio) {
        this.deleteAudio();

        SQLiteDatabase db = this.getWritableDatabase();
        for(int i=0; i<audio.size(); i++){

            ContentValues contentValues = new ContentValues();
            contentValues.put("sura", audio.get(i).getSura());
            contentValues.put("aya", audio.get(i).getAya());
            contentValues.put("text", audio.get(i).getText());

            Log.i(TAG, audio.get(i).toString());

            db.insert("audio", null, contentValues);
        }

        return true;
    }
    public boolean addAllPronunciation (ArrayList<PRO> pros) {
        this.deletepronunciation();

        SQLiteDatabase db = this.getWritableDatabase();
        for(int i=0; i<pros.size(); i++){

            ContentValues contentValues = new ContentValues();
            contentValues.put("sura", pros.get(i).getSura());
            contentValues.put("aya", pros.get(i).getAya());
            contentValues.put("text", pros.get(i).getText());

            Log.i(TAG, pros.get(i).toString());

            db.insert("pronunciation", null, contentValues);
        }

        return true;
    }

    public boolean addAllNames (ArrayList<NAMES> names) {
        this.deleteNames();

        SQLiteDatabase db = this.getWritableDatabase();
        for(int i=0; i<names.size(); i++){

            ContentValues contentValues = new ContentValues();
            contentValues.put("sura", names.get(i).getSura());
            contentValues.put("text", names.get(i).getText());

            Log.i(TAG, names.get(i).toString());

            db.insert("names", null, contentValues);
        }

        return true;
    }
    public boolean addAllSURA (ArrayList<Sura> suras) {
        this.deleteSURA();

        SQLiteDatabase db = this.getWritableDatabase();
        Gson gson = new Gson();

        for(int i=0; i<suras.size(); i++){

            ContentValues contentValues = new ContentValues();
            contentValues.put("sura_no", suras.get(i).getSura_no());
            contentValues.put("arabic_json", gson.toJson(suras.get(i).getArabics()));
            contentValues.put("bangla_json", gson.toJson(suras.get(i).getBanglas()));
            contentValues.put("pro_json", gson.toJson(suras.get(i).getPros()));
            contentValues.put("audio_json", gson.toJson(suras.get(i).getAudio()));

            Log.i(TAG, suras.get(i).toString());

            db.insert("SURA", null, contentValues);
        }

        return true;
    }

    public boolean addAllSURA2 (ArrayList<SURAA> suras) {
        this.deleteSURA();

        SQLiteDatabase db = this.getWritableDatabase();
        Gson gson = new Gson();

        for(int i=0; i<suras.size(); i++){

            ContentValues contentValues = new ContentValues();
            contentValues.put("sura_no", suras.get(i).getSura_no());
            contentValues.put("arabic_json", gson.toJson(suras.get(i).getARABIC()));
            contentValues.put("bangla_json", gson.toJson(suras.get(i).getBANGLA()));
            contentValues.put("pro_json", gson.toJson(suras.get(i).getPRO()));
            contentValues.put("audio_json", gson.toJson(suras.get(i).getAUDIO()));

            Log.i(TAG, suras.get(i).toString());

            db.insert("SURA", null, contentValues);
        }

        return true;
    }

    public ArrayList<ARABIC> getAllArabicBySura(int sura) {
        ArrayList<ARABIC> arabics = new ArrayList<ARABIC>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from arabic1 WHERE sura = "+sura+" ORDER BY aya ASC", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            ARABIC arabic = new ARABIC();
            arabic.setSura(Integer.parseInt(res.getString(res.getColumnIndex("sura"))));
            arabic.setAya(Integer.parseInt(res.getString(res.getColumnIndex("aya"))));
            arabic.setText(res.getString(res.getColumnIndex("text")));
            arabics.add(arabic);

            res.moveToNext();
        }
        return arabics;
    }
    public ArrayList<AUDIO> getAllAudioBySura(int sura) {
        ArrayList<AUDIO> audios = new ArrayList<AUDIO>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from audio WHERE sura = "+sura+" ORDER BY aya ASC", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            AUDIO audio = new AUDIO();
            audio.setSura(Integer.parseInt(res.getString(res.getColumnIndex("sura"))));
            audio.setAya(Integer.parseInt(res.getString(res.getColumnIndex("aya"))));
            audio.setText(res.getString(res.getColumnIndex("text")));
            audios.add(audio);

            res.moveToNext();
        }
        return audios;
    }
    public ArrayList<BANGLA> getAllBanglaBySura(int sura) {
        ArrayList<BANGLA> arabics = new ArrayList<BANGLA>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from bangla1 WHERE sura = "+sura+" ORDER BY aya ASC", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            BANGLA arabic = new BANGLA();
            arabic.setSura(Integer.parseInt(res.getString(res.getColumnIndex("sura"))));
            arabic.setAya(Integer.parseInt(res.getString(res.getColumnIndex("aya"))));
            arabic.setText(res.getString(res.getColumnIndex("text")));
            arabics.add(arabic);

            res.moveToNext();
        }
        return arabics;
    }
    public ArrayList<PRO> getAllPronunciationBySura(int sura) {
        ArrayList<PRO> pros = new ArrayList<PRO>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from pronunciation WHERE sura = "+sura+" ORDER BY aya ASC", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            PRO pro = new PRO();
            pro.setSura(Integer.parseInt(res.getString(res.getColumnIndex("sura"))));
            pro.setAya(Integer.parseInt(res.getString(res.getColumnIndex("aya"))));
            pro.setText(res.getString(res.getColumnIndex("text")));
            pros.add(pro);

            res.moveToNext();
        }
        return pros;
    }

    public SURAA getSURAByno(int sura_no) {
        SURAA sura = new SURAA();

        Gson gson = new Gson();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from SURA WHERE sura_no = "+sura_no, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            sura.setSura_no(Integer.parseInt(res.getString(res.getColumnIndex("sura_no"))));
            sura.setARABIC(gson.fromJson(res.getString(res.getColumnIndex("arabic_json")), ArrayList.class));
            sura.setBANGLA(gson.fromJson(res.getString(res.getColumnIndex("bangla_json")), ArrayList.class));
            sura.setPRO(gson.fromJson(res.getString(res.getColumnIndex("pro_json")), ArrayList.class));
            sura.setAUDIO(gson.fromJson(res.getString(res.getColumnIndex("audio_json")), ArrayList.class));

            res.moveToNext();
        }
        return sura;
    }

    public Sura getSURAByno2(int sura_no) {
        Sura sura = new Sura();

        Gson gson = new Gson();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from SURA WHERE sura_no = "+sura_no, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            sura.setSura_no(Integer.parseInt(res.getString(res.getColumnIndex("sura_no"))));
            sura.setArabics(new ArrayList<ARABIC>(Arrays.asList(gson.fromJson(res.getString(res.getColumnIndex("arabic_json")), ARABIC[].class)))); //gson.fromJson(res.getString(res.getColumnIndex("arabic_json")), ArrayList.class)
            sura.setBanglas(gson.fromJson(res.getString(res.getColumnIndex("bangla_json")), BANGLA_LIST.class).getBangla_json());
            sura.setPros(gson.fromJson(res.getString(res.getColumnIndex("pro_json")), ArrayList.class));
            sura.setAudio(gson.fromJson(res.getString(res.getColumnIndex("audio_json")), ArrayList.class));
            //new ArrayList<ARABIC>(Arrays.asList(gson.fromJson(res.getString(res.getColumnIndex("arabic_json")), ARABIC[].class)))

            for(int i=0; i<sura.getArabics().size(); i++){
                Log.i(TAG, sura.getArabics().get(i).getText());
            }

            for(int i=0; i<sura.getBanglas().size(); i++){
                Log.i(TAG, sura.getBanglas().get(i).getText());
            }

            res.moveToNext();
        }
        return sura;
    }


    public ArrayList<NAMES> getAllNames() {
        ArrayList<NAMES> names = new ArrayList<NAMES>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from names", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            NAMES arabic = new NAMES();
            arabic.setSura(Integer.parseInt(res.getString(res.getColumnIndex("sura"))));
            arabic.setText(res.getString(res.getColumnIndex("text")));
            names.add(arabic);

            res.moveToNext();
        }
        return names;
    }


}

