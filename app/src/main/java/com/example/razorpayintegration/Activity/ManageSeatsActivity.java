package com.example.razorpayintegration.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.razorpayintegration.Others.BottomSheetDialog;
import com.example.razorpayintegration.Interface.RequestApi;
import com.example.razorpayintegration.Model.Essentials;
import com.example.razorpayintegration.Model.Hospital;
import com.example.razorpayintegration.Model.Opd_data;
import com.example.razorpayintegration.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

   public class ManageSeatsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, BottomSheetDialog.BottomSheetListener {
    private RequestApi requestApi;
    private List<String> Select_Departments;
    private Spinner spinner;
    private String username;
    private CalendarView calendarView;
       private TextView currentnoOfseats;
    private Button editSeats;
    private ArrayAdapter<String> dataAdapter;
    private String date;
    private String seats;


       @Override
       public void onButtonClicked(String text) {
           currentnoOfseats.setText("Current Seats Available: "+text);
       }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        username = getIntent().getStringExtra("username");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_seats);
        requestApi = Essentials.getRequestApi();
        Select_Departments = new ArrayList<>();
        spinner = findViewById(R.id.spinner_id);
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Select_Departments);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);
        getHospitals(username);
        date= DateFormat.getDateInstance().format(new Date()).substring(0,2);
        calendarView = findViewById(R.id.calendar);
        currentnoOfseats = findViewById(R.id.seats_available);
        editSeats = findViewById(R.id.edit_seats_btn);
        Calendar cal = Calendar.getInstance();
        final long milliTime = cal.getTimeInMillis();
  //      calendarView.setDate(milliTime, true, true);
        calendarView.setMinDate(milliTime);

        editSeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog();
                bottomSheetDialog.show(getSupportFragmentManager(),"examplebottomsheet");
            }
        });


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                Log.d("Cal:", "onSelectedDayChange: here");
                date = "";
                date += day;
                date += "/";
                date += Integer.toString(month + 1);
                date += "/";
                date += year;
            }
        });
    }

    private void getHospitals(String Username) {
        Call<Hospital> call = requestApi.getHospital(Username);
        call.enqueue(new Callback<Hospital>() {
            @Override
            public void onResponse(Call<Hospital> call, Response<Hospital> response) {
                if (!response.isSuccessful()) {
                    Log.d("Code:", String.valueOf(response.code()));
                    return;
                }
                Select_Departments.clear();
                Select_Departments.add("Select Department");
                String[] dept = response.body().getDepartment().split(",");
                for (int i = 0; i < dept.length; i++) {
                    Select_Departments.add(dept[i]);
                }

                dataAdapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<Hospital> call, Throwable t) {
                Log.d("On Failure", t.getMessage());
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = Select_Departments.get(position);
        getOpd(username, item, date);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void getOpd(String username, String department, String date) {
        Call<List<Opd_data>> call = requestApi.getOpd(username, department, date);
        call.enqueue(new Callback<List<Opd_data>>() {
            @Override
            public void onResponse(Call<List<Opd_data>>call, Response<List<Opd_data>> response) {
                if (!response.isSuccessful()) {
                    Log.d("Code:", String.valueOf(response.code()));
                    return;
                }
                if(!response.body().isEmpty())
                {
                    seats = response.body().get(0).getSeats();
                    currentnoOfseats.setText("Current Seats Available: "+seats);
                }


            }

            @Override
            public void onFailure(Call<List<Opd_data>> call, Throwable t) {
                Log.d("OnFAilure", t.getMessage());
            }
        });
    }

}

