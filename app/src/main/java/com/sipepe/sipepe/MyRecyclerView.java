package com.sipepe.sipepe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerView extends RecyclerView.Adapter<MyRecyclerView.MyViewHolder>{
    public ArrayList<Jadwal> myJadwal;
    public Context context;
    public MyRecyclerView(Context context, ArrayList<Jadwal> myJadwal){
        this.context=context;
        this.myJadwal=myJadwal;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_cardview_event,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerView.MyViewHolder myViewHolder, int i) {
        Jadwal jadwal=myJadwal.get(i);
        myViewHolder.tanggal.setText(jadwal.getTanggal());
        myViewHolder.acara.setText(jadwal.getAcara());
        myViewHolder.waktu.setText(jadwal.getWaktu());
        myViewHolder.ruang.setText(jadwal.getRuang());
        myViewHolder.nim.setText(jadwal.getNim());
        myViewHolder.nama.setText(jadwal.getNama());
        myViewHolder.kode_acara.setText(jadwal.getKodeAcara());
        myViewHolder.kode_ruang.setText(jadwal.getKodeRuang());
        myViewHolder.kode_jadwal.setText(jadwal.getKodeJadwal());
        myViewHolder.jadwal=jadwal;
    }

    @Override
    public int getItemCount() {
        return myJadwal.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Jadwal jadwal;
        TextView acara,nama,nim,waktu,ruang,tanggal,kode_acara,kode_ruang,kode_jadwal;
        public MyViewHolder(View view){
            super(view);
            tanggal=view.findViewById(R.id.tanggal);
            acara=view.findViewById(R.id.acara);
            waktu=view.findViewById(R.id.waktu);
            ruang=view.findViewById(R.id.ruang);
            nim=view.findViewById(R.id.nim);
            nama=view.findViewById(R.id.nama);
            kode_acara=view.findViewById(R.id.kode_acara);
            kode_ruang=view.findViewById(R.id.kode_ruang);
            kode_jadwal=view.findViewById(R.id.kode_jadwal);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,FormAgenda.class);
                    intent.putExtra("tanggal",jadwal.getTanggal());
                    intent.putExtra("acara",jadwal.getAcara());
                    intent.putExtra("waktu",jadwal.getWaktu());
                    intent.putExtra("ruang",jadwal.getRuang());
                    intent.putExtra("nim",jadwal.getNim());
                    intent.putExtra("kode_acara",jadwal.getKodeAcara());
                    intent.putExtra("kode_ruang",jadwal.getKodeRuang());
                    intent.putExtra("kode_jadwal",jadwal.getKodeJadwal());
                    intent.putExtra("status","0");
                    context.startActivity(intent);
                }
            });
        }
    }
}
