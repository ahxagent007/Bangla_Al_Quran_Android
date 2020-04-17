package com.secretdevbd.dexian.banglaalquran.DB;

import java.util.ArrayList;

public class DDAATTAA {

    ArrayList<NAMES> NAMES = new ArrayList<NAMES>();
    ArrayList<SURAA> SURA = new ArrayList<SURAA>();

    public DDAATTAA() {
    }

    public ArrayList<com.secretdevbd.dexian.banglaalquran.DB.NAMES> getNAMES() {
        return NAMES;
    }

    public void setNAMES(ArrayList<com.secretdevbd.dexian.banglaalquran.DB.NAMES> NAMES) {
        this.NAMES = NAMES;
    }

    public ArrayList<SURAA> getSURA() {
        return SURA;
    }

    public void setSURA(ArrayList<SURAA> SURA) {
        this.SURA = SURA;
    }
}
