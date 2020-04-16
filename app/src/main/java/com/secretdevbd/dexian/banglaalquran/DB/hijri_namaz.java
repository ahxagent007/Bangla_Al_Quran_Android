package com.secretdevbd.dexian.banglaalquran.DB;

public class hijri_namaz {
    String date;
    weekday_namaz weekday;

    public hijri_namaz() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public weekday_namaz getWeekday() {
        return weekday;
    }

    public void setWeekday(weekday_namaz weekday) {
        this.weekday = weekday;
    }
}
