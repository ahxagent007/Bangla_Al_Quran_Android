package com.secretdevbd.dexian.banglaalquran.DB;

import java.util.ArrayList;

public class Sura {

    int sura_no;
    ArrayList<ARABIC> arabics = new ArrayList<ARABIC>();
    ArrayList<BANGLA> banglas = new ArrayList<BANGLA>();
    ArrayList<PRO> pros = new ArrayList<PRO>();

    public Sura() {
    }

    public int getSura_no() {
        return sura_no;
    }

    public void setSura_no(int sura_no) {
        this.sura_no = sura_no;
    }

    public ArrayList<ARABIC> getArabics() {
        return arabics;
    }

    public void setArabics(ArrayList<ARABIC> arabics) {
        this.arabics = arabics;
    }

    public ArrayList<BANGLA> getBanglas() {
        return banglas;
    }

    public void setBanglas(ArrayList<BANGLA> banglas) {
        this.banglas = banglas;
    }

    public ArrayList<PRO> getPros() {
        return pros;
    }

    public void setPros(ArrayList<PRO> pros) {
        this.pros = pros;
    }
}
