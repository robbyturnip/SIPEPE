package com.sipepe.sipepe;

import java.util.Calendar;

public class SimpanPilihanTanggal {
    public static int year,month,day,dayweek,numberMonth;
    public static Calendar calendar;
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
    public int getNumberMonth(){
        return numberMonth;
    }
    public Calendar getLastCalendar(){
        return calendar;
    }
    public void setLastCalendar(Calendar calendar){
        this.calendar=calendar;
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
    public void setNumberMonth(int numberMonth){
        this.numberMonth=numberMonth;
    }
    public void setCondition(boolean condition){
        this.condition=condition;
    }
}
