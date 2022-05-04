package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowTaskDetails extends AppCompatActivity implements View.OnClickListener {
    EditText etTaskDescription;
    TextView tvTaskDescription;
    TextView tvTaskName;
    Button btStartTask;
    Button btEditTaskDescription;
    Button btEditTaskDescriptionDone;
    String TaskName;
    String TaskDescription;
    int taskId;
    Boolean TaskDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task_details);

        //as Intent was created in class today and the information was stored in it
        //here we retrieve these information

        TaskName = getIntent().getStringExtra("TaskName");
        TaskDescription = getIntent().getStringExtra("TaskDetails");
        //if id >= 0 then treat form as edit mode otherwise its will be new task
        taskId = getIntent().getIntExtra("taskId",-1);
        TaskDone = getIntent().getBooleanExtra("done",false);

        tvTaskName = findViewById(R.id.tvTaskNameClick);
        tvTaskDescription = findViewById(R.id.tvTaskDescription);
        etTaskDescription = findViewById(R.id.etTaskDescription);
        btStartTask = findViewById(R.id.startTask);
        btEditTaskDescription = findViewById(R.id.btEditTaskDescription);
        btEditTaskDescriptionDone = findViewById(R.id.btEditTaskDescriptionDone);

        tvTaskName.setText(TaskName);
        tvTaskDescription.setText(TaskDescription);
        btStartTask.setOnClickListener(this);
        btEditTaskDescription.setOnClickListener(this);
        btEditTaskDescriptionDone.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String etDescription = etTaskDescription.getText().toString();
        TaskModel taskModel = findTaskUsingForLoop(taskId);
        switch (view.getId()) {
            case R.id.btEditTaskDescription:
                etTaskDescription.setVisibility(View.VISIBLE);
                btEditTaskDescriptionDone.setVisibility(View.VISIBLE);
                etTaskDescription.requestFocus();
                break;
            case R.id.btEditTaskDescriptionDone:
                if(taskModel!=null){
                    tvTaskDescription.append("\n\t"+etDescription);
                    taskModel.setTaskDetails(tvTaskDescription.getText().toString());
                    etTaskDescription.getText().clear();
                    etTaskDescription.setVisibility(View.INVISIBLE);
                    btEditTaskDescriptionDone.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.startTask:
                startActivity(new Intent(this, pomodoroTimer.class));
                break;

        }

    }
    public static TaskModel findTaskUsingForLoop(int task) {
        //looping through the list and return the task which id matches
        //every task is given unique id

        for (TaskModel Task : Task_RecyclerViewAdapter.taskModelArrayList) {
            if (Task.getTaskId() == task) {
                return Task;
            }
        }
        return null;
    }
}