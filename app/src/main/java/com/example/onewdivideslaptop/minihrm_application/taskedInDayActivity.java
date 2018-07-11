package com.example.onewdivideslaptop.minihrm_application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.Locale;

public class taskedInDayActivity extends AppCompatActivity {

    String dayMonthYear;
    int day,month,year;
    TextView dayShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasked_in_day);

        dayShow = (TextView) findViewById(R.id.dayShow);
        dayMonthYear = getIntent().getStringExtra("monthYear");
        day = getIntent().getExtras().getInt("day");
        month = getIntent().getExtras().getInt("month");
        year = getIntent().getExtras().getInt("year");
//        Log.e("testGetExtra : ",day+" "+monthYear);
        dayShow.setText(dayMonthYear);
        Log.e("day month year : ",day+" "+month+" "+year);



    }
}
