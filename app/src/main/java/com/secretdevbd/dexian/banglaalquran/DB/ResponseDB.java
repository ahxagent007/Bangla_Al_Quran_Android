package com.secretdevbd.dexian.banglaalquran.DB;

import java.util.ArrayList;

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

    public ArrayList<Sura> getSuraList(){
        int SURA_NO = 0;
        ArrayList<Sura> suras = new ArrayList<>();

       for(int i=0; i<data.getNAMES().size();i++){
           suras.add(new Sura());
       }

       for(int i=0;i<data.getARABIC().size();i++){
           SURA_NO = data.getARABIC().get(i).getSura();
           suras.get(SURA_NO-1).setSura_no(SURA_NO);

           suras.get(SURA_NO-1).arabics.add(data.getARABIC().get(i));
           suras.get(SURA_NO-1).banglas.add(data.getBANGLA().get(i));
           suras.get(SURA_NO-1).pros.add(data.getPRO().get(i));
           suras.get(SURA_NO-1).audio.add(data.getAUDIO().get(i));
       }

       return suras;

    }


}
