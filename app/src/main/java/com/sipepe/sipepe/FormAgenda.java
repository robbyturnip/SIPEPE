package com.sipepe.sipepe;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class FormAgenda extends AppCompatActivity {
    Toolbar toolbar;
    TextView tanggal;
    EditText waktu;
    Spinner acara,ruang;
    AutoCompleteTextView nim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_agenda);
        toolbar=findViewById(R.id.form_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tanggal=findViewById(R.id.tanggal);
        acara=findViewById(R.id.sp_acara);
        waktu=findViewById(R.id.waktu);
        ruang=findViewById(R.id.sp_ruang);
        nim=findViewById(R.id.nim);

//        waktu edittextview onclick
        waktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int hour=calendar.get(Calendar.HOUR_OF_DAY);
                int minute=calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog;
                timePickerDialog=new TimePickerDialog(FormAgenda.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        waktu.setText(String.format("%02d:%02d",hourOfDay,minute)+" WIB");
                    }
                },hour,minute,true);
                timePickerDialog.setTitle("Pilih Waktu");
                timePickerDialog.show();
            }
        });

//        Terima Parsing Data Intent
        tanggal.setText(getIntent().getStringExtra("tanggal"));
        acara.setSelection(((ArrayAdapter)acara.getAdapter()).getPosition(getIntent().getStringExtra("acara")));
        waktu.setText(getIntent().getStringExtra("waktu"));
        ruang.setSelection(((ArrayAdapter)ruang.getAdapter()).getPosition(getIntent().getStringExtra("ruang")));
        nim.setText(getIntent().getStringExtra("nim"));
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.form_menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id= menuItem.getItemId();
        if(id==R.id.simpan){
            Intent intent=new Intent(FormAgenda.this,MainActivity.class);
            intent.putExtra("acara",acara.getSelectedItem().toString());
            intent.putExtra("nim",nim.getText().toString());
            intent.putExtra("nama","Robby Maulana Turnip");
            intent.putExtra("tanggal",tanggal.getText().toString());
            intent.putExtra("waktu",waktu.getText().toString());
            intent.putExtra("ruang",ruang.getSelectedItem().toString());
            startActivity(intent);
            return true;
        }
        else if (id==R.id.cancel){
            Intent intent=new Intent(FormAgenda.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        return  super.onOptionsItemSelected(menuItem);
    }
}
