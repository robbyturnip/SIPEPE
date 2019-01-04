package com.sipepe.sipepe;

public class SimpanPilihanTanggal {
    public static int year,month,day,dayweek;
    static boolean  condition;
    public SimpanPilihanTanggal(){}

    public Boolean  getCondition(){
        return condition;
    }
    public int getYear(){
        return year;
    }
    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }
    public int getDayweek(){
        return dayweek;
    }
    public void setDayweek(int dayweek){
        this.dayweek=dayweek;
    }
    public void setYear(int year){
        this.year=year;
    }
    public void setMonth(int month){
        this.month=month;
    }
    public void setDay(int day){
        this.day=day;
    }
    public void setCondition(boolean condition){
        this.condition=condition;
    }
}
