package com.sipepe.sipepe;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by robby on 12/01/19.
 */

public class AdapterMahasiswa extends ArrayAdapter<Mahasiswa> {
    public Context context;
    public ArrayList<Mahasiswa> mahasiswas,temp,suggest;
    public AdapterMahasiswa(Context context, ArrayList<Mahasiswa> mahasiswas){
        super(context,  android.R.layout.simple_list_item_1, mahasiswas);
        this.context=context;
        this.mahasiswas=mahasiswas;
        this.temp=new ArrayList<Mahasiswa>(mahasiswas);
        this.suggest=new ArrayList<Mahasiswa>(mahasiswas);
    }
    @Override
    public int getCount(){
        return mahasiswas.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Mahasiswa mahasiswa = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.activity_adapter_mahasiswa, parent, false);
        }
        TextView nim = (TextView) convertView.findViewById(R.id.tvNim);
        nim.setTextColor(Color.BLACK);
        nim.setText(mahasiswa.getNim());
        TextView nama = (TextView) convertView.findViewById(R.id.tvNama);
        nama.setText(mahasiswa.getNama());
        nama.setTextColor(Color.BLACK);
        if (position % 2 == 0)
        convertView.setBackgroundColor(getContext().getColor(R.color.odd));
        else
            convertView.setBackgroundColor(getContext().getColor(R.color.even));
        return convertView;
    }
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Mahasiswa mahasiswa = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.activity_adapter_mahasiswa, parent, false);
        }
        TextView nim = (TextView) convertView.findViewById(R.id.tvNim);
        nim.setTextColor(Color.BLACK);
        nim.setText(mahasiswa.getNim());
        TextView nama = (TextView) convertView.findViewById(R.id.tvNama);
        nama.setText(mahasiswa.getNama());
        nama.setTextColor(Color.BLACK);
        if (position % 2 == 0)
            convertView.setBackgroundColor(getContext().getColor(R.color.odd));
        else
            convertView.setBackgroundColor(getContext().getColor(R.color.even));
        return convertView;
    }


    @Override
    public Filter getFilter() {
        return (Filter) myFilter;
    }

    Filter myFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Mahasiswa mahasiswa = (Mahasiswa) resultValue;
            return mahasiswa.getNim();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggest.clear();
                for (Mahasiswa mahasiswa : temp) {
                    if (mahasiswa.getNim().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggest.add(mahasiswa);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = suggest;
                filterResults.count = suggest.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Mahasiswa> c = (ArrayList<Mahasiswa>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Mahasiswa cust : c) {
                    add(cust);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
