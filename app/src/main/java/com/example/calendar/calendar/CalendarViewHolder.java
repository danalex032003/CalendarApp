package com.example.calendar.calendar;

import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.R;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final TextView dayOfMonth;
    private final CalendarAdapter.OnItemListener onItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cell_tv);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    public TextView getDayOfMonth() {
        return dayOfMonth;
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), dayOfMonth);
    }
}
