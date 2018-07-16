package com.example.onewdivideslaptop.minihrm_application;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onewdivideslaptop.minihrm_application.responseAndBody.calendarEventResponse;
import com.example.onewdivideslaptop.minihrm_application.responseAndBody.loginResponse;
import com.example.onewdivideslaptop.minihrm_application.responseAndBody.task_list_response;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.CalendarDayEvent;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class calendarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private SimpleDateFormat fragmentDateFormat = new SimpleDateFormat("dd MMMM yyyy",Locale.getDefault());
    TextView showMonthYear;
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    public ArrayList<Integer> taskedDay = new ArrayList<>();
    public Dialog dialog;
    int tempMonthToAddInCalendar;
    View view;
    TextView dateInPopup;
    String tempDate;
    String[] parts;
    public String urlencode = "application/x-www-form-urlencoded";
    private RecyclerView recyclerView;
    private List<task_list_response> listtask = new ArrayList<>();
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://minihrm-205709.appspot.com")
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();

    public Button addTaskBtn;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v =  inflater.inflate(R.layout.fragment_calendar, container, false);


        dialog = new Dialog(v.getContext());

//        taskedDay.add(12);
//        taskedDay.add(15);
//        taskedDay.add(15);
//        taskedDay.add(17);




        showMonthYear = (TextView) v.findViewById(R.id.showMonthYear);

        staticData.tabIndex = 0;

        compactCalendarView = (CompactCalendarView) v.findViewById(R.id.compactcalendar_view);
        compactCalendarView.drawSmallIndicatorForEvents(true);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        showMonthYear.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        timeStampCompare(System.currentTimeMillis()/1000);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

//                intent.putExtra("monthYear",putExtraDateFormate.format(dateClicked).toString());
//                intent.putExtra("day",dateClicked.getDate());
//                intent.putExtra("month",dateClicked.getMonth()+1);
//                intent.putExtra("year",dateClicked.getYear()+1900);

                timeStampCompare(System.currentTimeMillis()/1000);

                tempDate = fragmentDateFormat.format(dateClicked).toString();

                listtask.add(new task_list_response("Project 1","eiei 1","07:00"));
                listtask.add(new task_list_response("Project 2","eiei 2","08:00"));
                listtask.add(new task_list_response("Project 3","eiei 3","09:00"));
                listtask.add(new task_list_response("Project 4","eiei 4","07:00"));
                listtask.add(new task_list_response("Project 5","eiei 5","08:00"));
                listtask.add(new task_list_response("Project 6","eiei 6","09:00"));

                dialog.setContentView(R.layout.tasked_popup);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
                dialog.show();

                dateInPopup = (TextView) dialog.findViewById(R.id.date_id);
                dateInPopup.setText(tempDate);

                taskedRecyclerViewAdapter taskedRecyclerViewAdapter = new taskedRecyclerViewAdapter(getContext(),listtask);
                recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_tasked);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(taskedRecyclerViewAdapter);

                addTaskBtn = (Button) dialog.findViewById(R.id.addTaskBtn);
                addTaskBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(),addTaskActivity.class);
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                showMonthYear.setText(dateFormatForMonth.format(firstDayOfNewMonth));
                Log.e("Debug!! : ",dateFormatForMonth.format(firstDayOfNewMonth));

                timeStampCompare(System.currentTimeMillis()/1000);

                getEventInMonth("Bearer "+staticData.getToken(),
                        firstDayOfNewMonth.getYear()+1900,
                        firstDayOfNewMonth.getMonth()+1,
                        staticData.getId()
                );

            }
        });

        getEventInMonth("Bearer "+staticData.getToken(),
                compactCalendarView.getFirstDayOfCurrentMonth().getYear()+1900,
                compactCalendarView.getFirstDayOfCurrentMonth().getMonth()+1,
                staticData.getId()
        );

        return v;
    }


    private void addDummyEvents() {

        Log.e("tempMonth",String.valueOf(tempMonthToAddInCalendar));
        addEvents(compactCalendarView, tempMonthToAddInCalendar-1,taskedDay);

        // Refresh calendar to update events
        compactCalendarView.invalidate();
    }


    // Adding events from 1 to 6 days

    private void addEvents(CompactCalendarView compactCalendarView, int month, ArrayList<Integer> day) {
        currentCalender.setTime(new Date());
        currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = currentCalender.getTime();

        for (int i=0; i<day.size();i++) {
            currentCalender.setTime(firstDayOfMonth);
            currentCalender.set(Calendar.MONTH, month);
            currentCalender.add(Calendar.DATE, day.get(i) - 1);
            setToMidnight(currentCalender);
            compactCalendarView.addEvent(new CalendarDayEvent(currentCalender.getTimeInMillis(), Color.argb(255, 0, 0, 0)), false);
        }
    }



    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }


    private void getEventInMonth (String token,int year, int month, int userId){
        miniHRMClient miniHRMClient = retrofit.create(miniHRMClient.class);

        Call<List<calendarEventResponse>> call = miniHRMClient.viewEvent(urlencode,token,year,month,userId);

//        Log.e("urlencode",urlencode);
//        Log.e("token",token);
        Log.e("year",String.valueOf(year));
        Log.e("month",String.valueOf(month));
//        Log.e("userId",String.valueOf(userId));

        taskedDay.clear();

        tempMonthToAddInCalendar = month;

        call.enqueue(new Callback<List<calendarEventResponse>>() {
            @Override
            public void onResponse(Call<List<calendarEventResponse>> call, Response<List<calendarEventResponse>> response) {
                if (response.body() == null){
                    Log.e("add TaskedDay : ","null");
                }else{
                    for (int i=0; i<response.body().size(); i++){
                        parts = response.body().get(i).getDate().split("-");
                        if(i==0){
                            Log.e("add TaskedDay if : ",parts[2]);
                            taskedDay.add(Integer.parseInt(parts[2]));
                        }else{
                            if (!taskedDay.get(i-1).equals(parts[2])){
                                Log.e("add TaskedDay else : ",parts[2]);
                                taskedDay.add(Integer.parseInt(parts[2]));
                            }
                        }

                    }
                    addDummyEvents();
                }
            }

            @Override
            public void onFailure(Call<List<calendarEventResponse>> call, Throwable t) {
                Log.e("Failure : ",t.toString());
            }
        });

    }

    public void timeStampCompare(Long timeStampNow){
        if (timeStampNow - staticData.timeStampCompare >3000 ){
            staticData.timeStampCompare = timeStampNow;

            //get new token!!

            miniHRMClient miniHRMClient = retrofit.create(miniHRMClient.class);

            Call<loginResponse> call = miniHRMClient.newToken(staticData.refreshToken);

            call.enqueue(new Callback<loginResponse>() {
                @Override
                public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                    staticData.token = response.body().getToken();
                }

                @Override
                public void onFailure(Call<loginResponse> call, Throwable t) {

                }
            });

        }


    }
}
