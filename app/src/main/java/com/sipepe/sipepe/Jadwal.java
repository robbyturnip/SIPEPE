package com.sipepe.sipepe;

public class Jadwal {
    String acara,nama,nim,judul,tanggal,waktu,ruang,narasumber1,narasumber2,narasumber3,kode_acara,kode_ruang,kode_jadwal,tanggalDatabase;
    public Jadwal(){}
    public void setAcara(String acara){
        this.acara=acara;
    }
    public  void setNama(String nama){
        this.nama=nama;
    }
    public void setNim(String nim){
        this.nim=nim;
    }
    public void setJudul(String judul){
        this.judul=judul;
    }
    public void setTanggal(String tanggal){this.tanggal=tanggal; }
    public void setWaktu(String waktu){
        this.waktu=waktu;
    }
    public void  setRuang(String ruang){this.ruang=ruang;}
    public void setNarasumber1(String narasumber1){
        this.narasumber1=narasumber1;
    }
    public void setNarasumber2(String narasumber2){
        this.narasumber2=narasumber2;
    }
    public void setNarasumber3(String narasumber3){
        this.narasumber3=narasumber3;
    }
    public void setKodeAcara(String kode_acara){
        this.kode_acara=kode_acara;
    }
    public void  setKodeRuang(String kode_ruang){this.kode_ruang=kode_ruang;}
    public void  setKodeJadwal(String kode_jadwal){this.kode_jadwal=kode_jadwal;}
    public void  setTanggalDatabase(String tanggalDatabase){this.tanggalDatabase=tanggalDatabase;}
    public String getTanggalDatabase(){
        return tanggalDatabase;
    }
    public String getKodeJadwal(){
        return kode_jadwal;
    }
    public String getAcara(){
        return acara;
    }
    public String getNama(){
        return nama;
    }
    public String getNim(){
        return nim;
    }
    public String getJudul(){
        return judul;
    }
    public String getTanggal(){return tanggal;}
    public String getWaktu(){
        return waktu;
    }
    public String getRuang(){
        return ruang;
    }
    public String getNarasumber1(){
        return narasumber1;
    }
    public String getNarasumber2(){
        return narasumber2;
    }
    public String getNarasumber3(){
        return narasumber3;
    }
    public String getKodeAcara(){
        return kode_acara;
    }
    public String getKodeRuang(){
        return kode_ruang;
    }


}
