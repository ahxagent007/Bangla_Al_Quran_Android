package com.secretdevbd.dexian.banglaalquran.DB;

public class NAMES {
    int sura;
    String text;

    public NAMES() {
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
        return "NAMES{" +
                "sura=" + sura +
                ", text='" + text + '\'' +
                '}';
    }
}
