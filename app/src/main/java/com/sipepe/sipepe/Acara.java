package com.sipepe.sipepe;

/**
 * Created by robby on 05/01/19.
 */

public class Acara {
    String acara;
    int kodeAcara;
    public Acara(){
        this.kodeAcara=0;
        this.acara="";
    }
    public void setAcara(String acara){
        this.acara=acara;
    }
    public void setKodeAcara(int kodeAcara){
        this.kodeAcara=kodeAcara;
    }
    public String getAcara(){
        return acara;
    }
    public int getKodeAcara(){
        return kodeAcara;
    }

}
