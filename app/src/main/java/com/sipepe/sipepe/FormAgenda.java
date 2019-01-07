package com.sipepe.sipepe;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.annimon.stream.Stream;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.applandeo.materialcalendarview.utils.DateUtils;
import com.sipepe.sipepe.Util.AppController;
import com.sipepe.sipepe.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormAgenda extends AppCompatActivity {
    Toolbar toolbar;
    EditText tanggal;
    EditText waktu;
    Spinner acara,ruang;
    AutoCompleteTextView nim;
    ArrayList<Ruang> ruangs;
    ProgressDialog pd;
    Acara[] myAcara;
    Ruang[] myRuang;
    protected SpinAdapterAcara spinAdapterAcara;
    protected SpinAdapterRuang spinAdapterRuang;
    public int kodeAcara;
    public String kodeRuang,selectedDay,selectedMonth,selectedDate,tanggalDatabase,waktuSekarang;
    int dayOfMonth,dayOfWeek,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_agenda);
        toolbar=findViewById(R.id.form_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tanggal=findViewById(R.id.tanggal);
        waktu=findViewById(R.id.waktu);
        nim=findViewById(R.id.nim);
        pd=new ProgressDialog(FormAgenda.this);
        loadSpinner();

//        Toast.makeText(getApplicationContext(),getIntent().getStringExtra("tanggalDatabase"),Toast.LENGTH_SHORT).show();
//


//        waktu edittextview onclick

        waktu.setOnClickListener(new View.OnClickListener() {
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

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpanPilihanTanggalFormAgenda simpanPilihanTanggalFormAgenda = new SimpanPilihanTanggalFormAgenda();

                if (simpanPilihanTanggalFormAgenda.getConditionTanggal()) {
                    dayOfWeek = simpanPilihanTanggalFormAgenda.getDayweek();
                    dayOfMonth = simpanPilihanTanggalFormAgenda.getDay();
                    month = simpanPilihanTanggalFormAgenda.getMonth();
                    year = simpanPilihanTanggalFormAgenda.getYear();
                    Calendar dateSelected = DateUtils.getCalendar();
                    dateSelected.set(year, month, dayOfMonth);
                        DatePickerBuilder builder = new DatePickerBuilder(FormAgenda.this, new OnSelectDateListener() {

                            @Override
                            public void onSelect(List<Calendar> calendar) {
                                Stream.of(calendar).forEach(calendars -> {

                                            dayOfWeek = calendars.get(Calendar.DAY_OF_WEEK);
                                            dayOfMonth = calendars.get(Calendar.DAY_OF_MONTH);
                                            month = calendars.get(Calendar.MONTH);
                                            year = calendars.get(Calendar.YEAR);

                                        }
                                );
//
                                switch (dayOfWeek) {
                                    case Calendar.SUNDAY:
                                        selectedDay = "Minggu";
                                        break;
                                    case Calendar.MONDAY:
                                        selectedDay = "Senin";
                                        break;
                                    case Calendar.TUESDAY:
                                        selectedDay = "Selasa";
                                        break;
                                    case Calendar.WEDNESDAY:
                                        selectedDay = "Rabu";
                                        break;
                                    case Calendar.THURSDAY:
                                        selectedDay = "Kamis";
                                        break;
                                    case Calendar.FRIDAY:
                                        selectedDay = "Jumat";
                                        break;
                                    case Calendar.SATURDAY:
                                        selectedDay = "Sabtu";
                                        break;
                                }
                                switch (month) {
                                    case Calendar.JANUARY:
                                        selectedMonth = "Januari";
                                        break;
                                    case Calendar.FEBRUARY:
                                        selectedMonth = "Februari";
                                        break;
                                    case Calendar.MARCH:
                                        selectedMonth = "Maret";
                                        break;
                                    case Calendar.APRIL:
                                        selectedMonth = "April";
                                        break;
                                    case Calendar.MAY:
                                        selectedMonth = "Mei";
                                        break;
                                    case Calendar.JUNE:
                                        selectedMonth = "Juni";
                                        break;
                                    case Calendar.JULY:
                                        selectedMonth = "Juli";
                                        break;
                                    case Calendar.AUGUST:
                                        selectedMonth = "Agustus";
                                        break;
                                    case Calendar.SEPTEMBER:
                                        selectedMonth = "September";
                                        break;
                                    case Calendar.OCTOBER:
                                        selectedMonth = "Oktober";
                                        break;
                                    case Calendar.NOVEMBER:
                                        selectedMonth = "November";
                                        break;
                                    case Calendar.DECEMBER:
                                        selectedMonth = "Desember";
                                }
                                selectedDate = selectedDay + ", " + dayOfMonth + " " + selectedMonth + " " + year;
                                tanggalDatabase = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth);

                                simpanPilihanTanggalFormAgenda.setYear(year);
                                simpanPilihanTanggalFormAgenda.setMonth(month);
                                simpanPilihanTanggalFormAgenda.setDay(dayOfMonth);
                                simpanPilihanTanggalFormAgenda.setDayweek(dayOfWeek);
                                simpanPilihanTanggalFormAgenda.setConditionTanggal(true);
//                                 Toast.makeText(getApplicationContext(),tanggalDatabase,Toast.LENGTH_SHORT).show();
                                tanggal.setText(selectedDate);
                            }
                        }).pickerType(CalendarView.ONE_DAY_PICKER);
                        builder.date(dateSelected);
                        DatePicker datePicker = builder.build();
                        datePicker.show();
                }
                else{
                    DatePickerBuilder builder = new DatePickerBuilder(FormAgenda.this, new OnSelectDateListener() {

                        @Override
                        public void onSelect(List<Calendar> calendar) {
                            Stream.of(calendar).forEach(calendars -> {

                                        dayOfWeek = calendars.get(Calendar.DAY_OF_WEEK);
                                        dayOfMonth = calendars.get(Calendar.DAY_OF_MONTH);
                                        month = calendars.get(Calendar.MONTH);
                                        year = calendars.get(Calendar.YEAR);

                                    }
                            );
//
                            switch (dayOfWeek) {
                                case Calendar.SUNDAY:
                                    selectedDay = "Minggu";
                                    break;
                                case Calendar.MONDAY:
                                    selectedDay = "Senin";
                                    break;
                                case Calendar.TUESDAY:
                                    selectedDay = "Selasa";
                                    break;
                                case Calendar.WEDNESDAY:
                                    selectedDay = "Rabu";
                                    break;
                                case Calendar.THURSDAY:
                                    selectedDay = "Kamis";
                                    break;
                                case Calendar.FRIDAY:
                                    selectedDay = "Jumat";
                                    break;
                                case Calendar.SATURDAY:
                                    selectedDay = "Sabtu";
                                    break;
                            }
                            switch (month) {
                                case Calendar.JANUARY:
                                    selectedMonth = "Januari";
                                    break;
                                case Calendar.FEBRUARY:
                                    selectedMonth = "Februari";
                                    break;
                                case Calendar.MARCH:
                                    selectedMonth = "Maret";
                                    break;
                                case Calendar.APRIL:
                                    selectedMonth = "April";
                                    break;
                                case Calendar.MAY:
                                    selectedMonth = "Mei";
                                    break;
                                case Calendar.JUNE:
                                    selectedMonth = "Juni";
                                    break;
                                case Calendar.JULY:
                                    selectedMonth = "Juli";
                                    break;
                                case Calendar.AUGUST:
                                    selectedMonth = "Agustus";
                                    break;
                                case Calendar.SEPTEMBER:
                                    selectedMonth = "September";
                                    break;
                                case Calendar.OCTOBER:
                                    selectedMonth = "Oktober";
                                    break;
                                case Calendar.NOVEMBER:
                                    selectedMonth = "November";
                                    break;
                                case Calendar.DECEMBER:
                                    selectedMonth = "Desember";
                            }
                            selectedDate = selectedDay + ", " + dayOfMonth + " " + selectedMonth + " " + year;
                            tanggalDatabase = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth);

                            simpanPilihanTanggalFormAgenda.setYear(year);
                            simpanPilihanTanggalFormAgenda.setMonth(month);
                            simpanPilihanTanggalFormAgenda.setDay(dayOfMonth);
                            simpanPilihanTanggalFormAgenda.setDayweek(dayOfWeek);
                            simpanPilihanTanggalFormAgenda.setConditionTanggal(true);
//                                 Toast.makeText(getApplicationContext(),tanggalDatabase,Toast.LENGTH_SHORT).show();
                            tanggal.setText(selectedDate);
                        }
                    }).pickerType(CalendarView.ONE_DAY_PICKER);
                    DatePicker datePicker = builder.build();
                    datePicker.show();
                }
            }
        });

//        Terima Parsing Data Intent
        tanggal.setText(getIntent().getStringExtra("tanggal"));
        if(getIntent().getStringExtra("waktu")!=null) {
            waktu.setText(getIntent().getStringExtra("waktu"));
        }else{
            waktu.setText(getIntent().getStringExtra("waktuSekarang"));
        }
        nim.setText(getIntent().getStringExtra("nim"));

    }

    public void loadSpinner(){
        loadAcara();
        loadRuang();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.form_menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id= menuItem.getItemId();
        if(id==R.id.simpan){

            if(getIntent().getStringExtra("status").equals("1")) {
                insertJadwal();
//                Toast.makeText(getApplicationContext(),((waktu.getText().toString()).substring(0,5))+":00",Toast.LENGTH_SHORT).show();
            }
            else{
                updateJadwal();
//                Toast.makeText(getApplicationContext(),getIntent().getStringExtra("kode_jadwal"),Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        else if (id==R.id.cancel){
            if(getIntent().getStringExtra("status").equals("1")) {
                cancelJadwal();
            }
            else{
                deleteJadwal();

            }
        }
        return  super.onOptionsItemSelected(menuItem);
    }

    private void cancelJadwal() {
        Intent intent=new Intent(FormAgenda.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void deleteJadwal() {

        pd.setMessage("Delete Jadwal");
        pd.setCancelable(false);
        pd.show();


        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_DELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
//                            Toast.makeText(FormAgenda.this, "pesan : "+   res.getString("pesan") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(FormAgenda.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
//                        Toast.makeText(FormAgenda.this, "pesan : Gagal Delete Jadwal", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("kode_jadwal",getIntent().getStringExtra("kode_jadwal"));
                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);


    }

    private void updateJadwal() {
        pd.setMessage("Update Jadwal");
        pd.setCancelable(false);
        pd.show();


        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        Log.d("tampil","response : " + response.toString());
                        try {
                            JSONObject res = new JSONObject(response);
                            Log.d("tampil","response : " + res.getString("pesan"));
//                            Toast.makeText(getApplicationContext(), "pesan : "+   res.getString("pesan") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(FormAgenda.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
//                        Toast.makeText(FormAgenda.this, "pesan : Gagal Update Jadwal", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nim",nim.getText().toString());
                map.put("kode_acara",kodeAcara+"");
                map.put("kode_ruang",kodeRuang);
                map.put("tanggal",tanggalDatabase);
                map.put("waktu",((waktu.getText().toString()).substring(0,5))+":00");
                map.put("kode_jadwal",getIntent().getStringExtra("kode_jadwal"));
                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
        Toast.makeText(getApplicationContext(),tanggalDatabase,Toast.LENGTH_SHORT).show();

    }

    private void insertJadwal() {

        pd.setMessage("Menyimpan Jadwal");
        pd.setCancelable(false);
        pd.show();


        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_CREATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        Log.d("tampil","response : " + response.toString());
                        try {
                            JSONObject res = new JSONObject(response);
                            Log.d("tampil","response : " + res.getString("pesan"));
//                            Toast.makeText(FormAgenda.this, "pesan : "+   res.getString("pesan") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(FormAgenda.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
//                        Toast.makeText(FormAgenda.this, "pesan : Gagal Tambah Jadwal", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nim",nim.getText().toString());
                map.put("kode_acara",kodeAcara+"");
                map.put("kode_ruang",kodeRuang);
                map.put("tanggal",tanggalDatabase);
                map.put("waktu",((waktu.getText().toString()).substring(0,5))+":00");
                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }

    public int loadAcara(){

        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_READ_ACARA,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("tampil","response : " + response.toString());
                        myAcara=new Acara[response.length()];
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                myAcara[i]=new Acara();
                                JSONObject acaraJson = response.getJSONObject(i);
                                myAcara[i].setAcara(acaraJson.getString("acara"));
                                myAcara[i].setKodeAcara(Integer.parseInt(acaraJson.getString("kode_acara")));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        spinAdapterAcara=new SpinAdapterAcara(getApplicationContext(),android.R.layout.simple_spinner_item,myAcara);
                        spinAdapterAcara.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        acara=findViewById(R.id.sp_acara);
                        acara.setAdapter(spinAdapterAcara);
                        if(getIntent().getStringExtra("kode_acara")!=null) {
                            for (int i = 0; i < spinAdapterAcara.getCount(); i++) {
                                if (Integer.parseInt(getIntent().getStringExtra("kode_acara"))==spinAdapterAcara.getItem(i).kodeAcara) {
                                    acara.setSelection(spinAdapterAcara.getPosition(spinAdapterAcara.getItem(i)));
                                }
                            }
                        }
                        acara.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Acara acara=spinAdapterAcara.getItem(position);
                                kodeAcara=acara.getKodeAcara();
//                                Toast.makeText(FormAgenda.this,"Kode Acara : "+acara.getKodeAcara()+"\nAcara :"+acara.getAcara(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.cancel();
                Log.d("tampil", "error : " + error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(reqData);
        return kodeAcara;
    }


    public String loadRuang(){

        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();


        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_READ_RUANG,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("tampil","response : " + response.toString());
                        myRuang=new Ruang[response.length()];
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                myRuang[i]=new Ruang();
                                JSONObject ruangJson = response.getJSONObject(i);
                                myRuang[i].setRuang(ruangJson.getString("ruang"));
                                myRuang[i].setKodeRuang(ruangJson.getString("kode_ruang"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        spinAdapterRuang=new SpinAdapterRuang(getApplicationContext(),android.R.layout.simple_spinner_item,myRuang);
                        spinAdapterRuang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        ruang=findViewById(R.id.sp_ruang);
                        ruang.setAdapter(spinAdapterRuang);
//                        Toast.makeText(FormAgenda.this,"Ruang : "+getIntent().getStringExtra("kode_ruang"),Toast.LENGTH_SHORT).show();
                        if(getIntent().getStringExtra("kode_ruang")!=null) {
                            for (int i = 0; i < spinAdapterRuang.getCount(); i++) {
                                if (getIntent().getStringExtra("kode_ruang").equals(spinAdapterRuang.getItem(i).kodeRuang)) {
                                    ruang.setSelection(spinAdapterRuang.getPosition(spinAdapterRuang.getItem(i)));
                                }
                            }
                        }
                        ruang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Ruang ruang=spinAdapterRuang.getItem(position);
                                kodeRuang=ruang.getKodeRuang();
//                                Toast.makeText(FormAgenda.this,"Kode Ruang : "+ruang.getKodeRuang()+"\nRuang :"+ruang.getRuang(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.cancel();
                Log.d("tampil", "error : " + error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(reqData);
        return kodeRuang;
    }


}
