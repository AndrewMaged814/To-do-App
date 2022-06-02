package com.example.myapplication.task.Activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.task.Adapters.CustomAdapter;
import com.example.myapplication.task.Priorities.HighPriority;
import com.example.myapplication.task.Priorities.LowPriority;
import com.example.myapplication.task.Priorities.ModeratePriority;
import com.example.myapplication.task.Adapters.RowItem;
import com.example.myapplication.task.Task;
import com.example.myapplication.task.Tasks_Store;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CreateNewTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerCat;
    private Spinner spinnerPri;
    private static final String[] c = {"   Task category", "Study", "Sport", "hobby", "other"};
    private static final String[] p = {"   Task Priority","Low", "Medium", "High"};
    private static final int[] iconsP = {R.drawable.icon,R.mipmap.ic_cold, R.mipmap.ic_medium, R.mipmap.ic_fire};
    private static final int[] iconsC = {R.drawable.icon,R.mipmap.ic_study,R.mipmap.ic_sport,R.mipmap.ic_hobby,R.mipmap.ic_other};
    private Button Add;
    private String cat;

    private int year, month, day;
    private EditText input;
    private String Priority;
    private EditText D;
    final Calendar myCal = Calendar.getInstance();
    String date;
    Task task;
    Tasks_Store sharedPreferences;


    @SuppressLint("ResourceType")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        sharedPreferences = new Tasks_Store(this);

        //dropdown category menu
        List<RowItem> rowItemsC = new ArrayList<RowItem>();
        for (int i = 0; i < c.length; i++) {

            RowItem item = new RowItem(c[i], iconsC[i]);
            rowItemsC.add(item);
        }

        spinnerCat = (Spinner) findViewById(R.id.spinnerCat);
        CustomAdapter A_C = new CustomAdapter(CreateNewTask.this,
                R.layout.spinner_with_icons, R.id.text, rowItemsC);
        spinnerCat.setAdapter(A_C);
        spinnerCat.setOnItemSelectedListener(this);

        //  A_C.setDropDownViewResource(R.drawable.spinner_options);



        List<RowItem> rowItemsP = new ArrayList<RowItem>();
        for (int i = 0; i < p.length; i++) {

            RowItem item = new RowItem(p[i], iconsP[i]);
            rowItemsP.add(item);
        }

        spinnerPri = (Spinner) findViewById(R.id.spinnerPri);
        CustomAdapter A_P = new CustomAdapter(CreateNewTask.this,
                R.layout.spinner_with_icons, R.id.text, rowItemsP);
        spinnerPri.setAdapter(A_P);
        // A_P.setDropDownViewResource(R.drawable.spinner_options);

        spinnerPri.setOnItemSelectedListener(this);


        //Date
        D = (EditText) findViewById(R.id.PickDate);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        D.setShowSoftInputOnFocus(false);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int Year, int Month, int Day) {
                myCal.set(Calendar.YEAR, Year);
                myCal.set(Calendar.MONTH, Month);
                myCal.set(Calendar.DAY_OF_MONTH, Day);
                day = Day;
                month = Month + 1;
                year = Year;
                Update();
            }
        };


        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePicker = new DatePickerDialog(CreateNewTask.this,style,date,myCal.get(Calendar.YEAR), myCal.get(Calendar.MONTH), myCal.get(Calendar.DAY_OF_MONTH));
                datePicker.show();
            }
        });


        //Add button
        Add = findViewById(R.id.button);
        input = findViewById(R.id.EditTaskName);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTask(view);
            }
        });


    }



    private void Update() {
        String Format = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(Format, Locale.US);
        D.setText(dateFormat.format(myCal.getTime()));

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        if (parent.getId() == R.id.spinnerCat)
            cat = parent.getItemAtPosition(pos).toString();
        if (parent.getId() == R.id.spinnerPri)
            Priority = parent.getItemAtPosition(pos).toString();

    }


    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), "please enter task Category", Toast.LENGTH_LONG).show();


    }

    //Add Button
    public void AddTask(View view) {
        String Name = input.getText().toString();
        date = D.getText().toString();

        if (spinnerCat.getSelectedItemPosition() == 0) {
            onNothingSelected(spinnerCat);
        } else if (Name.equals("")) {
            Toast.makeText(getApplicationContext(), "please enter task name", Toast.LENGTH_LONG).show();
        } else if (date.equals("")) {
            Toast.makeText(getApplicationContext(), "please enter the date", Toast.LENGTH_LONG).show();

        } else {

            input.setHint("Task name");
            input.setText("");

            D.setHint("PickDate");
            D.setText("");
            spinnerCat.setSelection(0);
            spinnerPri.setSelection(0);

            if (Priority.contains("Low")) {
                task = new LowPriority(Name, cat, Priority, day, month, year, date);
            } else if (Priority.contains("Medium")) {


                task = new ModeratePriority(Name, cat, Priority, day, month, year, date);
            } else if (Priority.contains("High")) {

                task = new HighPriority(Name, cat, Priority, day, month, year, date);


            }
            sharedPreferences.store(task);


        }

    }

}

