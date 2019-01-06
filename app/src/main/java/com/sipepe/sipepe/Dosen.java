package com.sipepe.sipepe;

/**
 * Created by robby on 05/01/19.
 */

public class Dosen {
    String idDosen,dosen;
    public Dosen(){}
    public void setDosen(String dosen){
        this.dosen=dosen;
    }
    public void setIdDosen(String idDosen){
        this.idDosen=idDosen;
    }
    public String getDosen(){
        return dosen;
    }
    public String getIdDosen(){
        return idDosen;
    }
    @Override
    public String toString() {
        return dosen;
    }
}
