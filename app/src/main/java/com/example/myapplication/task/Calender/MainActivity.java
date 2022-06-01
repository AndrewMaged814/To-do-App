package com.example.myapplication.task.Calender;

//import static com.example.calenderapp.CalendarUtils.daysInMonthArray;
//import static com.example.calenderapp.CalendarUtils.monthYearFormDate;

import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapplication.R;
import com.example.myapplication.task.Task;
import com.example.myapplication.task.Tasks_Store;
import com.example.myapplication.task.taskTypes.DelayedTask;
import com.example.myapplication.task.taskTypes.Today;
import com.example.myapplication.task.taskTypes.UpComing;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.task.Calender.CalendarUtils.daysInMonthArray;
import static com.example.myapplication.task.Calender.CalendarUtils.monthYearFormDate;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    public static List<Task> eventsList = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();


        mergeArrayList();
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();
    }

    private void mergeArrayList() {
        eventsList.addAll(Today.TodTasks);
        eventsList.addAll(DelayedTask.DelayedTasks);
        eventsList.addAll(UpComing.UpComingTasks);
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calenderRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView()
    {
        monthYearText.setText(monthYearFormDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager= new GridLayoutManager(getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);

    }


    //when <- is clicked
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();

    }

    //when -> is clicked
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, LocalDate date)
    {
        if(date != null)
        {
        CalendarUtils.selectedDate = date;
        setMonthView();
        }
        //String message = "Selected Date" + date + "" + monthYearFormDate(CalendarUtils.selectedDate);
        //Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }

    public void weeklyAction(View view)
    {
        startActivity(new Intent(this, WeekViewActivity.class));
    }
}