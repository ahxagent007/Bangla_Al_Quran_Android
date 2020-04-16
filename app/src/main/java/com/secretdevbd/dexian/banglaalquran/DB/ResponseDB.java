package com.secretdevbd.dexian.banglaalquran.DB;

public class ResponseDB {
    dataa data;
    int status;

    public ResponseDB() {
    }

    public dataa getData() {
        return data;
    }

    public void setData(dataa data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
