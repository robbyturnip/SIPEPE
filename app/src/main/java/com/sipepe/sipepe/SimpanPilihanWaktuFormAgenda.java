package com.sipepe.sipepe;

import java.util.Calendar;

/**
 * Created by robby on 07/01/19.
 */

public class SimpanPilihanWaktuFormAgenda {
    public static int hour,minute;
    static boolean  conditionWaktu;

    public SimpanPilihanWaktuFormAgenda(){}
    public void setConditionWaktu(boolean conditionWaktu){this.conditionWaktu=conditionWaktu;}
    public Boolean  getConditionWaktu(){
        return conditionWaktu;
    }
    public void setHour(int hour){
        this.hour=hour;
    }
    public void setMinute(int minute){
        this.minute=minute;
    }
    public int getHour(){
        return hour;
    }
    public int getMinute(){
        return minute;
    }


}
