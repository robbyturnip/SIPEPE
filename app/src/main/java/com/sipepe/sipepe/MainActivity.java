package com.sipepe.sipepe;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    SimpanPilihanTanggal simpanPilihanTanggal;
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    CalendarView calendarView;
    RecyclerView recyclerView;
    MyRecyclerView myRecyclerView;
    ArrayList<Jadwal> jadwals;

    int dayOfMonth,dayOfWeek,month,year;
    String selectedDay,selectedMonth,selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jadwals=new ArrayList<>();

//        load tanggal terakhir dipilih
        simpanPilihanTanggal=new SimpanPilihanTanggal();

//        Load Event
        loadEvent();

//        Inisiasi Class
        simpanPilihanTanggal=new SimpanPilihanTanggal();

//        Inisiasi id dari xml activity_main
        toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        floatingActionButton=findViewById(R.id.add);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        recyclerView=findViewById(R.id.recyclerView);

        myRecyclerView=new MyRecyclerView(jadwals);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myRecyclerView);



//        Awal Setting CalendarView Library Applandeo
        Calendar min = Calendar.getInstance();
        min.add(Calendar.YEAR, -100);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.YEAR, 100);

        calendarView.setMinimumDate(min);
        calendarView.setMaximumDate(max);

        List<EventDay> events = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
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
            simpanPilihanTanggal.setCondition(true);
            Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_SHORT).show();

        }

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
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
                startActivity(intent);
            }
        });



    }

    public void loadEvent(){
        Jadwal jadwal=new Jadwal();
        jadwal.setTanggal(getIntent().getStringExtra("tanggal"));
        jadwal.setAcara(getIntent().getStringExtra("acara"));
        jadwal.setWaktu(getIntent().getStringExtra("waktu"));
        jadwal.setRuang(getIntent().getStringExtra("ruang"));
        jadwal.setNim(getIntent().getStringExtra("nim"));
        jadwal.setNama(getIntent().getStringExtra("nama"));
        jadwals.add(jadwal);
    }
}
