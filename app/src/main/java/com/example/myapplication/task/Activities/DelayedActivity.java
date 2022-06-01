package com.example.myapplication.task.Activities;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.example.myapplication.R;
import com.example.myapplication.task.Adapters.RecyclerViewInterface;
import com.example.myapplication.task.Adapters.Task_RecyclerViewAdapter;
import com.example.myapplication.task.Task;
import com.example.myapplication.task.Tasks_Store;
import com.example.myapplication.task.taskTypes.DelayedTask;
import com.example.myapplication.task.taskTypes.Today;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DelayedActivity extends AppCompatActivity implements RecyclerViewInterface {
    static ArrayList<Task> taskModelArrayList= DelayedTask.DelayedTasks;

    Task_RecyclerViewAdapter adapter;
    Tasks_Store sharedPreferences;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delayed);
        getCurrentDate();
        lottieAnimationView = findViewById(R.id.lottieAnimationceleberation);
        RecyclerView recyclerView = findViewById(R.id.mRecyclerViewDelayed);
        adapter = new Task_RecyclerViewAdapter(this,taskModelArrayList,this,lottieAnimationView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sharedPreferences = new Tasks_Store(this);
    }

    private void getCurrentDate() {
        //to get the current date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.LONG).format(calendar.getTime());
        TextView tvDate = findViewById(R.id.tvTodayDate1);
        tvDate.setText(currentDate);
    }
    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(DelayedActivity.this,ShowTaskDetails.class);

        intent.putExtra("TaskName",taskModelArrayList.get(position).getTaskName());
        intent.putExtra("TaskDetails",taskModelArrayList.get(position).getTaskDetails());
        intent.putExtra("Image",taskModelArrayList.get(position).getImage());
        intent.putExtra("done",taskModelArrayList.get(position).isTaskDone());
        intent.putExtra("taskId",taskModelArrayList.get(position).getTaskId());


        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        Task task = taskModelArrayList.get(position);
        task.DeleteTask(taskModelArrayList,task.getTaskName(), sharedPreferences);
        adapter.notifyItemRemoved(position);

    }
}