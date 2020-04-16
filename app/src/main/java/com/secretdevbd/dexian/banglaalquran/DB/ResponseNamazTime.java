package com.secretdevbd.dexian.banglaalquran.DB;

public class ResponseNamazTime {
    int code;
    String status;
    data_namaz data;

    public ResponseNamazTime() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public data_namaz getData() {
        return data;
    }

    public void setData(data_namaz data) {
        this.data = data;
    }
}
