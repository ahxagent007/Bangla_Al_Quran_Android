package com.secretdevbd.dexian.banglaalquran.DB;

public class ResponseDB {
    data data;
    int status;

    public ResponseDB() {
    }

    public com.secretdevbd.dexian.banglaalquran.DB.data getData() {
        return data;
    }

    public void setData(com.secretdevbd.dexian.banglaalquran.DB.data data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
