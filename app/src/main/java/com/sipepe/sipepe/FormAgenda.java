package com.sipepe.sipepe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class FormAgenda extends AppCompatActivity {
    Toolbar toolbar;
    TextView tanggal;
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
        ruang=findViewById(R.id.sp_ruang);
        nim=findViewById(R.id.nim);

//        Terima Parsing Data Intent
        tanggal.setText(getIntent().getStringExtra("tanggal"));
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.form_menu,menu);
        return true;
    }
}
