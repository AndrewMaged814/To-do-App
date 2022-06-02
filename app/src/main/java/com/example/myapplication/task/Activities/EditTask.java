package com.example.myapplication.task.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.task.Adapters.CustomAdapter;
import com.example.myapplication.task.Adapters.RowItem;
import com.example.myapplication.task.Priorities.HighPriority;
import com.example.myapplication.task.Priorities.LowPriority;
import com.example.myapplication.task.Priorities.ModeratePriority;
import com.example.myapplication.task.Task;
import com.example.myapplication.task.Tasks_Store;
import com.example.myapplication.task.taskTypes.Today;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class EditTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Spinner spinnerCat;
    private Spinner spinnerPri;
    private static final String[] paths = {"Task category","Study", "Sport", "hobby", "other"};
    private static final String [] p={"Low","Medium","High"};
    private static final int [] icons={R.mipmap.ic_cold,R.mipmap.ic_medium,R.mipmap.ic_fire};
    private Button edit;
    private String cat ;
    private int year,month,day;
    private EditText input;
    String OldName;
    private String Priority;
    private EditText D;
    final Calendar myCal =Calendar.getInstance();
    String date;
    Task task;
    Tasks_Store sharedPreferences;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task);

        Intent intent=getIntent();
        cat= intent.getStringExtra("Cat");
        Priority = intent.getStringExtra("Priority");
        date=intent.getStringExtra("Date");
        OldName=intent.getStringExtra("TaskName");

        for (Task t : Today.TodTasks) {
            if (t.Name.equals(OldName))
                Today.TodTasks.remove(t);
        }

        //dropdown category menu
        spinnerCat = (Spinner) findViewById(R.id.spinnerCat);
        spinnerCat.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditTask.this, android.R.layout.simple_spinner_item , paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCat.setAdapter(adapter);
        sharedPreferences=new Tasks_Store(this);
        for (int i=0; i<paths.length;i++) {
            if (cat.equals(paths[i]))
                spinnerCat.setSelection(i);
        }

        List<RowItem> rowItems=new ArrayList<RowItem>();
        for (int i = 0; i < p.length; i++) {

            RowItem item = new RowItem(p[i],icons[i]);
            rowItems.add(item);
        }

        //priority spinner
        spinnerPri = (Spinner) findViewById(R.id.spinnerPri);
        CustomAdapter A = new CustomAdapter(EditTask.this, R.layout.spinner_with_icons, R.id.text, rowItems);
        spinnerPri.setAdapter(A);
        spinnerPri.setOnItemSelectedListener(this);

        for (int i=0; i<p.length;i++) {
            if (Priority.equals(p[i]))
                spinnerPri.setSelection(i);
        }



        //Date
        D=(EditText)findViewById(R.id.PickDate);
        D.setText(date);
        int style= AlertDialog.THEME_HOLO_LIGHT;
        D.setShowSoftInputOnFocus(false);
        DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view , int Year, int Month, int Day) {
                myCal.set(Calendar.YEAR,Year);
                myCal.set(Calendar.MONTH,Month);
                myCal.set(Calendar.DAY_OF_MONTH,Day);
                day=Day;
                month=Month+1;
                year=Year;
                Update();
            }
        };


        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePicker=new DatePickerDialog(EditTask.this,style ,date, myCal.get(Calendar.YEAR), myCal.get(Calendar.MONTH), myCal.get(Calendar.DAY_OF_MONTH));
                datePicker.show();
            }
        });


        //EDIT button
        edit = findViewById(R.id.button);
        input = findViewById(R.id.EditTaskName);
        input.setText(OldName);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EDIT_Task(view);
                finish();

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
        if(parent.getId()==R.id.spinnerCat)
            cat=parent.getItemAtPosition(pos).toString();
        if(parent.getId()==R.id.spinnerPri)
            Priority=parent.getItemAtPosition(pos).toString();

    }



    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), "please enter task Category", Toast.LENGTH_LONG).show();


    }

    //EDIT Button
    public void EDIT_Task(View view) {
        String Name= input.getText().toString();
        date=D.getText().toString();

        if (spinnerCat.getSelectedItemPosition()==0) {
            onNothingSelected(spinnerCat);
        }
        else if (Name.equals("Name")) {
            Toast.makeText(getApplicationContext(), "please enter task name", Toast.LENGTH_LONG).show();
        }
        else if (date.equals("PickDate")){
            Toast.makeText(getApplicationContext(), "please enter the date", Toast.LENGTH_LONG).show();

        }
        else {

            input.setHint("Task name");
            input.setText("");

            D.setHint("PickDate");
            D.setText("");
            spinnerCat.setSelection(0);
            spinnerPri.setSelection(0);

            if (Priority.contains("Low")) {
                task =new LowPriority(Name,cat,Priority,day,month,year,date);
            } else if (Priority.contains("Medium")) {


                task =new ModeratePriority(Name,cat,Priority,day,month,year,date);
            } else if (Priority.contains("High")) {

                task = new HighPriority(Name, cat, Priority, day, month, year, date);


            }
            sharedPreferences.EditTask(OldName,task);
            Intent intent = new Intent(EditTask.this,TodayActivity.class);
            startActivity(intent);

        }

    }

}

