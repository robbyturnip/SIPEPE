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

public class SpinAdapterAcara extends ArrayAdapter<Acara> {

    public Context context;
    public Acara[] acaras;
    public SpinAdapterAcara(Context context, int textViewResourceId, Acara[] acaras){
        super(context, textViewResourceId, acaras);
        this.context=context;
        this.acaras=acaras;
    }

    @Override
    public int getCount(){
        return acaras.length;
    }
    @Override
    public Acara getItem(int position){
        return acaras[position];
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
        label.setText(acaras[position].getAcara());
        return label;
    }

    @Override
    public  View getDropDownView(int position,View convertView, ViewGroup parent){
        TextView label=(TextView)super.getDropDownView(position,convertView,parent);
        label.setTextColor(Color.BLACK);
        label.setText(acaras[position].getAcara());
        return label;
    }
}
