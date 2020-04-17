package com.secretdevbd.dexian.banglaalquran.DB;

import java.util.ArrayList;

public class AUDIO_LIST {
    ArrayList<AUDIO> audio_json = new ArrayList<AUDIO>();

    public AUDIO_LIST() {
    }

    public ArrayList<AUDIO> getAudio_json() {
        return audio_json;
    }

    public void setAudio_json(ArrayList<AUDIO> audio_json) {
        this.audio_json = audio_json;
    }
}
