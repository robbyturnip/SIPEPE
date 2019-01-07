package com.sipepe.sipepe;

import java.util.Calendar;

/**
 * Created by robby on 07/01/19.
 */

public class SimpanPilihanTanggalFormAgenda {
    public static int year,month,day,dayweek,numberMonth;
    public static Calendar calendar;
    static boolean  conditionTanggal;

    public SimpanPilihanTanggalFormAgenda(){}

    public void setConditionTanggal(boolean conditionTanggal){
        this.conditionTanggal=conditionTanggal;
    }
    public Boolean  getConditionTanggal(){
        return conditionTanggal;
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
    public int getDayweek(){return dayweek;}
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

}
