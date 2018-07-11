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
import android.widget.TextView;
import android.widget.Toast;

import com.example.onewdivideslaptop.minihrm_application.responseAndBody.task_list_response;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.CalendarDayEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class calendarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private SimpleDateFormat putExtraDateFormate = new SimpleDateFormat("dd MMMM yyyy",Locale.getDefault());
    TextView showMonthYear;
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    public ArrayList<Integer> taskedDay = new ArrayList<>();
    public Dialog dialog;
    View view;
    private RecyclerView recyclerView;
    private List<task_list_response> listtask = new ArrayList<>();





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v =  inflater.inflate(R.layout.fragment_calendar, container, false);


        dialog = new Dialog(v.getContext());

        taskedDay.add(12);
        taskedDay.add(15);
        taskedDay.add(17);


        showMonthYear = (TextView) v.findViewById(R.id.showMonthYear);


        compactCalendarView = (CompactCalendarView) v.findViewById(R.id.compactcalendar_view);
        compactCalendarView.drawSmallIndicatorForEvents(true);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        showMonthYear.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

//                Intent intent = new Intent(getActivity(),taskedInDayActivity.class);
//                intent.putExtra("monthYear",putExtraDateFormate.format(dateClicked).toString());
//                intent.putExtra("day",dateClicked.getDate());
//                intent.putExtra("month",dateClicked.getMonth()+1);
//                intent.putExtra("year",dateClicked.getYear()+1900);
//                startActivity(intent);


                listtask.add(new task_list_response("Project 1","eiei 1","07:00"));
                listtask.add(new task_list_response("Project 2","eiei 2","08:00"));
                listtask.add(new task_list_response("Project 3","eiei 3","09:00"));
                listtask.add(new task_list_response("Project 4","eiei 4","07:00"));
                listtask.add(new task_list_response("Project 5","eiei 5","08:00"));
                listtask.add(new task_list_response("Project 6","eiei 6","09:00"));

                dialog.setContentView(R.layout.tasked_popup);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
                dialog.show();

                taskedRecyclerViewAdapter taskedRecyclerViewAdapter = new taskedRecyclerViewAdapter(getContext(),listtask);
                recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_tasked);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(taskedRecyclerViewAdapter);



            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                showMonthYear.setText(dateFormatForMonth.format(firstDayOfNewMonth));
                Log.e("Debug!! : ",dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });

        addDummyEvents();

        return v;
    }


    private void addDummyEvents() {

        addEvents(compactCalendarView, Calendar.APRIL,taskedDay);
        addEvents(compactCalendarView, Calendar.MAY,taskedDay);
        addEvents(compactCalendarView, Calendar.JUNE,taskedDay);

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

}
