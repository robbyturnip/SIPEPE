package com.sipepe.sipepe;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by robby on 06/01/19.
 */

public class SpinAdapterRuang extends ArrayAdapter<Ruang> {
    public Context context;
    public Ruang[] ruangs;
    public SpinAdapterRuang(Context context, int textViewResourceId, Ruang[] ruangs){
        super(context, textViewResourceId, ruangs);
        this.context=context;
        this.ruangs=ruangs;
    }

    @Override
    public int getCount(){
        return ruangs.length;
    }
    @Override
    public Ruang getItem(int position){
        return ruangs[position];
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TextView label=(TextView) super.getView(position,convertView,parent);
        label.setTextColor(Color.BLACK);
        label.setTextSize(18);
        label.setText(ruangs[position].getRuang());
        return label;
    }

    @Override
    public  View getDropDownView(int position,View convertView, ViewGroup parent){
        TextView label=(TextView)super.getDropDownView(position,convertView,parent);
        label.setTextColor(Color.BLACK);
        label.setText(ruangs[position].getRuang());
        return label;
    }
}
