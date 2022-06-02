package com.example.myapplication.task.Calender;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import com.example.myapplication.R;
import com.example.myapplication.task.Task;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Task>
{
    public EventAdapter(@NonNull Context context, List<Task> events)
    {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Task event = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);
        CardView cardView = convertView.findViewById(R.id.calenderRecyclerView);

        String eventTitle = event.getTaskName();
        eventCellTV.setText(eventTitle);
        switch (event.Category) {
            case "Study":
                cardView.setCardBackgroundColor(Color.parseColor("#E46472"));
                break;
            case "Sport":
                cardView.setCardBackgroundColor(Color.parseColor("#6488e4"));
                break;
            case "hobby":
                cardView.setCardBackgroundColor(Color.parseColor("#309397"));
                break;
            case "other":
                cardView.setCardBackgroundColor(Color.parseColor("#f9be7c"));
                break;
        }

        return convertView;
    }
}
