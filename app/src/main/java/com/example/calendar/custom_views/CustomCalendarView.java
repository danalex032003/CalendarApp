package com.example.calendar.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.example.calendar.R;

public class CustomCalendarView extends RelativeLayout {

    private CalendarView calendarView;


    public CustomCalendarView(Context context) {
        super(context);
        init(context, null);
    }

    public CustomCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // Inflate the layout
        LayoutInflater.from(context).inflate(R.layout.custom_calendar_view, this, true);

        // Initialize the calendar view
        calendarView = findViewById(R.id.calendar_view);

//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//
//            }
//        });



    }





}
