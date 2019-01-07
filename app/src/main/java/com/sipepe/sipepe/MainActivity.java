package com.sipepe.sipepe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.utils.DateUtils;
import com.sipepe.sipepe.Util.ServerAPI;
import com.sipepe.sipepe.Util.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Integer.parseInt;


public class MainActivity extends AppCompatActivity {
//   inisiasi Class dan View
    SimpanPilihanTanggal simpanPilihanTanggal;
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    CalendarView calendarView;
    RecyclerView recyclerView;
    RecyclerView.Adapter rAdapter;
    RecyclerView.LayoutManager rLayoutManager;
    ArrayList<Jadwal> jadwals;
    ProgressDialog pd;


    int dayOfMonth,dayOfWeek,month,year;
    String selectedDay,selectedMonth,selectedDate,tanggalDatabase,waktuSekarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jadwals=new ArrayList<>();

//        load tanggal terakhir dipilih
        simpanPilihanTanggal=new SimpanPilihanTanggal();


//        Inisiasi Class
        simpanPilihanTanggal=new SimpanPilihanTanggal();

//        Inisiasi id dari xml activity_main
        toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        floatingActionButton=findViewById(R.id.add);
        calendarView =findViewById(R.id.calendarView);
        recyclerView=findViewById(R.id.recyclerView);
        pd=new ProgressDialog(MainActivity.this);


//        Load Event
        loadEvent();
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);
        waktuSekarang=String.format("%02d:%02d",hour,minute)+" WIB";


//        setting recycleView
        rLayoutManager=new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(rLayoutManager);
        rAdapter=new MyRecyclerView(MainActivity.this,jadwals);
        recyclerView.setAdapter(rAdapter);


//        Awal Setting CalendarView Library Applandeo
        Calendar min = Calendar.getInstance();
        min.add(Calendar.YEAR, -100);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.YEAR, 100);

        calendarView.setMinimumDate(min);
        calendarView.setMaximumDate(max);

        List<EventDay> events = new ArrayList<>();
        events.add(new EventDay(calendar, R.drawable.ic_event));
        calendarView.setEvents(events);

        if(simpanPilihanTanggal.getCondition()) {
            dayOfWeek = simpanPilihanTanggal.getDayweek();
            dayOfMonth = simpanPilihanTanggal.getDay();
            month = simpanPilihanTanggal.getMonth();
            year = simpanPilihanTanggal.getYear();
            Calendar dateSelected = DateUtils.getCalendar();
            dateSelected.set(year,month,dayOfMonth);
            try {
                calendarView.setDate(dateSelected);
            } catch (OutOfDateRangeException e) {
                e.printStackTrace();
            }

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
            tanggalDatabase=String.format("%d-%02d-%02d",year,month+1,dayOfMonth);
            simpanPilihanTanggal.setCondition(true);
            Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_SHORT).show();
        }
        else{
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
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
            tanggalDatabase=String.format("%d-%02d-%02d",year,month+1,dayOfMonth);
            SimpanPilihanTanggal simpanPilihanTanggal=new SimpanPilihanTanggal();
            simpanPilihanTanggal.setYear(year);
            simpanPilihanTanggal.setMonth(month);
            simpanPilihanTanggal.setDay(dayOfMonth);
            simpanPilihanTanggal.setDayweek(dayOfWeek);
            simpanPilihanTanggal.setCondition(true);
            Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_SHORT).show();

        }

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(eventDay.getCalendar().getTimeInMillis());
                dayOfWeek=calendar.get(Calendar.DAY_OF_WEEK);
                dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
                month=calendar.get(Calendar.MONTH);
                year=calendar.get(Calendar.YEAR);
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

                selectedDate=selectedDay+", "+dayOfMonth+" "+selectedMonth+" "+year;
                tanggalDatabase=String.format("%d-%02d-%02d",year,month+1,dayOfMonth);
                SimpanPilihanTanggal simpanPilihanTanggal=new SimpanPilihanTanggal();
                simpanPilihanTanggal.setYear(year);
                simpanPilihanTanggal.setMonth(month);
                simpanPilihanTanggal.setDay(dayOfMonth);
                simpanPilihanTanggal.setDayweek(dayOfWeek);
                simpanPilihanTanggal.setCondition(true);
                Toast.makeText(getApplicationContext(),  selectedDate , Toast.LENGTH_SHORT).show();
            }
        });
//        Akhir Setting CalendarView Library Applandeo


//        ini FloatingActionButton
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, FormAgenda.class);
                intent.putExtra("tanggal",selectedDate);
                intent.putExtra("tanggalDatabase",tanggalDatabase);
                intent.putExtra("waktuSekarang",waktuSekarang);
                intent.putExtra("status","1");
                startActivity(intent);
            }
        });



    }



    public void loadEvent(){
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_READ,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("tampil","response : " + response.toString());
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject event = response.getJSONObject(i);
                                String dataDay,dataMonth,dataDate;
                                dataDay="";
                                dataMonth="";
                                String formatTanggal=event.getString("tanggal");
                                int year=parseInt((formatTanggal).substring(0,4));
                                int month=parseInt((formatTanggal).substring(5,7))-1;
                                int dayOfMonth=parseInt((formatTanggal).substring(8,10));
                                Calendar calendar=Calendar.getInstance();
                                calendar.set(year,month,dayOfMonth);
                                int dayOfweek=calendar.get(Calendar.DAY_OF_WEEK);
                                switch (dayOfweek) {
                                    case Calendar.SUNDAY:
                                        dataDay = "Minggu";
                                        break;
                                    case Calendar.MONDAY:
                                        dataDay = "Senin";
                                        break;
                                    case Calendar.TUESDAY:
                                        dataDay = "Selasa";
                                        break;
                                    case Calendar.WEDNESDAY:
                                        dataDay = "Rabu";
                                        break;
                                    case Calendar.THURSDAY:
                                        dataDay = "Kamis";
                                        break;
                                    case Calendar.FRIDAY:
                                        dataDay = "Jumat";
                                        break;
                                    case Calendar.SATURDAY:
                                        dataDay = "Sabtu";
                                        break;
                                }
                                switch (month) {
                                    case Calendar.JANUARY:
                                        dataMonth = "Januari";
                                        break;
                                    case Calendar.FEBRUARY:
                                        dataMonth = "Februari";
                                        break;
                                    case Calendar.MARCH:
                                        dataMonth = "Maret";
                                        break;
                                    case Calendar.APRIL:
                                        dataMonth = "April";
                                        break;
                                    case Calendar.MAY:
                                        dataMonth = "Mei";
                                        break;
                                    case Calendar.JUNE:
                                        dataMonth = "Juni";
                                        break;
                                    case Calendar.JULY:
                                        dataMonth = "Juli";
                                        break;
                                    case Calendar.AUGUST:
                                        dataMonth = "Agustus";
                                        break;
                                    case Calendar.SEPTEMBER:
                                        dataMonth = "September";
                                        break;
                                    case Calendar.OCTOBER:
                                        dataMonth = "Oktober";
                                        break;
                                    case Calendar.NOVEMBER:
                                        dataMonth = "November";
                                        break;
                                    case Calendar.DECEMBER:
                                        dataMonth = "Desember";
                                }

                                dataDate = dataDay + ", " + dayOfMonth + " " + dataMonth + " " + year;
                                Jadwal jadwal = new Jadwal();
                                jadwal.setTanggal(dataDate);
                                jadwal.setAcara(event.getString("acara"));
                                jadwal.setWaktu((event.getString("waktu")).substring(0,5)+" WIB");
                                jadwal.setRuang(event.getString("ruang"));
                                jadwal.setNim(event.getString("nim"));
                                jadwal.setNama(event.getString("mahasiswa"));
                                jadwal.setKodeAcara(event.getString("kode_acara"));
                                jadwal.setKodeRuang(event.getString("kode_ruang"));
                                jadwal.setKodeJadwal(event.getString("kode_jadwal"));
                                jadwals.add(jadwal);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        rAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("tampil", "error : " + error.getMessage());
                    }
                });

        AppController.getInstance().addToRequestQueue(reqData);
    }
}
