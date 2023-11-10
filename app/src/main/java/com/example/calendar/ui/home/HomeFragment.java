package com.example.calendar.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.R;
import com.example.calendar.calendar.CalendarAdapter;
import com.example.calendar.databinding.FragmentHomeBinding;
import com.google.android.material.color.MaterialColors;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements CalendarAdapter.OnItemListener {

    private FragmentHomeBinding binding;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        //ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, null);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initWidgets();

        selectedDate = LocalDate.now();
        setMonthView();



        return root;
    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);

    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {

        ArrayList<String> daysInMonthArrayList = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        Integer daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        Integer dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 2; i <= 43; i++) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArrayList.add("");
            } else {
                daysInMonthArrayList.add(String.valueOf(i - dayOfWeek));
            }
        }

        return daysInMonthArrayList;
    }

    private String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    private void initWidgets() {
        calendarRecyclerView = binding.calendarRv;
        monthYearText = binding.monthYearTv;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, TextView dayOfMonth) {
        if (!dayOfMonth.getText().equals("")) {
            dayOfMonth.setBackgroundColor(MaterialColors.getColor(dayOfMonth, androidx.appcompat.R.attr.colorPrimary));
            String message = "Selected date: " + dayOfMonth.getText() + " " + monthYearFromDate(selectedDate);
            Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }



    }

}