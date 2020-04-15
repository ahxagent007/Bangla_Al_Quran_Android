package com.secretdevbd.dexian.banglaalquran.DB;

import java.util.ArrayList;

public class data {
    ArrayList<ARABIC> ARABIC = new ArrayList<ARABIC>();
    ArrayList<BANGLA> BANGLA = new ArrayList<BANGLA>();
    ArrayList<NAMES> NAMES = new ArrayList<NAMES>();
    ArrayList<PRO> PRO = new ArrayList<PRO>();
    ArrayList<AUDIO> AUDIO = new ArrayList<AUDIO>();

    public data() {
    }

    public ArrayList<com.secretdevbd.dexian.banglaalquran.DB.AUDIO> getAUDIO() {
        return AUDIO;
    }

    public void setAUDIO(ArrayList<com.secretdevbd.dexian.banglaalquran.DB.AUDIO> AUDIO) {
        this.AUDIO = AUDIO;
    }

    public ArrayList<com.secretdevbd.dexian.banglaalquran.DB.ARABIC> getARABIC() {
        return ARABIC;
    }

    public void setARABIC(ArrayList<com.secretdevbd.dexian.banglaalquran.DB.ARABIC> ARABIC) {
        this.ARABIC = ARABIC;
    }

    public ArrayList<com.secretdevbd.dexian.banglaalquran.DB.BANGLA> getBANGLA() {
        return BANGLA;
    }

    public void setBANGLA(ArrayList<com.secretdevbd.dexian.banglaalquran.DB.BANGLA> BANGLA) {
        this.BANGLA = BANGLA;
    }

    public ArrayList<com.secretdevbd.dexian.banglaalquran.DB.NAMES> getNAMES() {
        return NAMES;
    }

    public void setNAMES(ArrayList<com.secretdevbd.dexian.banglaalquran.DB.NAMES> NAMES) {
        this.NAMES = NAMES;
    }

    public ArrayList<com.secretdevbd.dexian.banglaalquran.DB.PRO> getPRO() {
        return PRO;
    }

    public void setPRO(ArrayList<com.secretdevbd.dexian.banglaalquran.DB.PRO> PRO) {
        this.PRO = PRO;
    }
}
