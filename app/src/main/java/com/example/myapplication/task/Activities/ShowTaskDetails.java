package com.example.myapplication.task.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.task.Activities.EditTask;
import com.example.myapplication.task.Adapters.Task_RecyclerViewAdapter;
import com.example.myapplication.task.Task;

public class ShowTaskDetails extends AppCompatActivity implements View.OnClickListener {
    EditText etTaskDescription;
    TextView tvTaskDescription;
    TextView tvTaskName;
    Button btStartTask;
    Button btEditTaskDescription;
    Button btEditTaskDescriptionDone;
    String TaskName;
    String TaskDate;
    String TaskCat;
    String TaskPri;
    String TaskDescription;
    Button addNotes;
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
        TaskDate = getIntent().getStringExtra("TaskDate");
        TaskPri = getIntent().getStringExtra("TaskPri");
        TaskCat = getIntent().getStringExtra("TaskCat");

        //if id >= 0 then treat form as edit mode otherwise its will be new task
        taskId = getIntent().getIntExtra("taskId",-1);
        TaskDone = getIntent().getBooleanExtra("done",false);

        tvTaskName = findViewById(R.id.tvTaskNameClick);
        tvTaskDescription = findViewById(R.id.tvTaskDescription);
        etTaskDescription = findViewById(R.id.etTaskDescription);
        btStartTask = findViewById(R.id.startTask);
        btEditTaskDescription = findViewById(R.id.btEditTaskDescription);
        btEditTaskDescriptionDone = findViewById(R.id.btEditTaskDescriptionDone);
        addNotes=findViewById(R.id.addNotes);

        tvTaskName.setText(TaskName);
        tvTaskDescription.setText(TaskDescription);
        btStartTask.setOnClickListener(this);
        btEditTaskDescription.setOnClickListener(this);
        btEditTaskDescriptionDone.setOnClickListener(this);
        addNotes.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String etDescription = etTaskDescription.getText().toString();
        Task taskModel = findTaskUsingForLoop(taskId);
        switch (view.getId()) {
            case R.id.btEditTaskDescription:
                Intent intent = new Intent(ShowTaskDetails.this, EditTask.class);
                intent.putExtra("TaskName",TaskName);
                intent.putExtra("Date",TaskDate);
                intent.putExtra("Priority",TaskPri);
                intent.putExtra("Cat",TaskCat);
                startActivity(intent);
                finish();

                break;
            case(R.id.addNotes):
                etTaskDescription.setVisibility(View.VISIBLE);
                btEditTaskDescriptionDone.setVisibility(View.VISIBLE);
                etTaskDescription.requestFocus();

                break;
            case R.id.btEditTaskDescriptionDone:


                if(taskModel!=null){
                    tvTaskDescription.append("\n"+etDescription);
                    taskModel.setTaskDetails(tvTaskDescription.getText().toString());
                    etTaskDescription.getText().clear();
                    etTaskDescription.setVisibility(View.INVISIBLE);
                    btEditTaskDescriptionDone.setVisibility(View.INVISIBLE);
                }

                break;
            case R.id.startTask:
                intent = new Intent(ShowTaskDetails.this, pomodoroTimer.class);
                intent.putExtra("TaskNamePomodoro",TaskName);
                startActivity(intent);
                break;


        }

    }
    public static Task findTaskUsingForLoop(int task) {
        //looping through the list and return the task which id matches
        //every task is given unique id

        for (com.example.myapplication.task.Task Task : Task_RecyclerViewAdapter.taskModelArrayList) {
            if (Task.getTaskId() == task) {
                return Task;
            }
        }
        return null;
    }
}