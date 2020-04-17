package com.secretdevbd.dexian.banglaalquran.DB;

import java.util.ArrayList;

public class SURAA {
    int sura_no;
    ArrayList<String> ARABIC = new ArrayList<String>();
    ArrayList<String> AUDIO = new ArrayList<String>();
    ArrayList<String> BANGLA = new ArrayList<String>();
    ArrayList<String> PRO = new ArrayList<String>();

    public SURAA() {
    }

    public int getSura_no() {
        return sura_no;
    }

    public void setSura_no(int sura_no) {
        this.sura_no = sura_no;
    }

    public ArrayList<String> getARABIC() {
        return ARABIC;
    }

    public void setARABIC(ArrayList<String> ARABIC) {
        this.ARABIC = ARABIC;
    }

    public ArrayList<String> getAUDIO() {
        return AUDIO;
    }

    public void setAUDIO(ArrayList<String> AUDIO) {
        this.AUDIO = AUDIO;
    }

    public ArrayList<String> getBANGLA() {
        return BANGLA;
    }

    public void setBANGLA(ArrayList<String> BANGLA) {
        this.BANGLA = BANGLA;
    }

    public ArrayList<String> getPRO() {
        return PRO;
    }

    public void setPRO(ArrayList<String> PRO) {
        this.PRO = PRO;
    }
}
