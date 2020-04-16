package com.secretdevbd.dexian.banglaalquran.DB;

public class PRO {
    int aya;
    int sura;
    String text;

    public PRO() {
    }

    public int getAya() {
        return aya;
    }

    public void setAya(int aya) {
        this.aya = aya;
    }

    public int getSura() {
        return sura;
    }

    public void setSura(int sura) {
        this.sura = sura;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "PRO{" +
                "aya=" + aya +
                ", sura=" + sura +
                ", text='" + text + '\'' +
                '}';
    }
}
