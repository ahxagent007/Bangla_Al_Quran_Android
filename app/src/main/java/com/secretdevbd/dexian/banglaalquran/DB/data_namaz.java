package com.secretdevbd.dexian.banglaalquran.DB;

public class data_namaz {
    timings_namaz timings;
    meta_namaz meta;
    date_namaz date;

    public data_namaz() {
    }

    public date_namaz getDate() {
        return date;
    }

    public void setDate(date_namaz date) {
        this.date = date;
    }

    public timings_namaz getTimings() {
        return timings;
    }

    public void setTimings(timings_namaz timings) {
        this.timings = timings;
    }

    public meta_namaz getMeta() {
        return meta;
    }

    public void setMeta(meta_namaz meta) {
        this.meta = meta;
    }
}
