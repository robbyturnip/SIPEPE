package com.sipepe.sipepe;

/**
 * Created by robby on 12/01/19.
 */

public class Mahasiswa {
    String nim,nama,skripsi,narasumber1,narasumber2,narasumber3;
    public Mahasiswa(){}
    public void setNim(String nim){this.nim=nim;}
    public void setNama(String nama){this.nama=nama;}
    public void setNarasumber1(String narasumber1){this.narasumber1=narasumber1;}
    public void setNarasumber2(String narasumber2){this.narasumber2=narasumber2;}
    public void setNarasumber3(String narasumber3){this.narasumber3=narasumber3;}
    public void setSkripsi(String skripsi){this.skripsi=skripsi;}
    public String getSkripsi(){return skripsi;}
    public String getNim(){return nim;}
    public String getNama(){return nama;}
    public String getNarasumber1(){return narasumber1;}
    public String getNarasumber2(){return narasumber2;}
    public String getNarasumber3(){return narasumber3;}
    @Override
    public String toString() {
        return this.nim;
    }
}
