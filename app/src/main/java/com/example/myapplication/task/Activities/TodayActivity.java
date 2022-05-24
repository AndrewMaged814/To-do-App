package com.example.myapplication.task.Activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapplication.R;
import com.example.myapplication.task.Adapters.RecyclerViewInterface;
import com.example.myapplication.task.Task;
import com.example.myapplication.task.Adapters.Task_RecyclerViewAdapter;
import com.example.myapplication.task.taskTypes.Today;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class TodayActivity extends AppCompatActivity implements RecyclerViewInterface {

    static ArrayList<Task> taskModelArrayList= Today.TodTasks;
    Task_RecyclerViewAdapter adapter;
    Menu menu;
    private final static String TAG = "TASK APP";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getCurrentDate();

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        //setup the the arraylist model before sending it out to the adapter
       // setTaskModelArrayList();
        adapter = new Task_RecyclerViewAdapter(this,taskModelArrayList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void getCurrentDate() {
        //to get the current date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.LONG).format(calendar.getTime());
        TextView tvDate = findViewById(R.id.tvTodayDate);
        tvDate.setText(currentDate);
    }

//    private void setTaskModelArrayList(){
//        //here I hardcoded the task details as for testing and setting up the today's page
//        //this method will be updated when the creation task form is created (take those fields as input from user)
//
//       /* Task t1 = new Task("Team meeting","@6pm",R.drawable.ic_baseline_edit_24,1);
//        Task t2 = new Task("Communication","not okay",R.drawable.ic_baseline_edit_24,2);
//        Task t3 = new Task("Gym","",R.drawable.ic_baseline_edit_24,3);
//        Task t4 = new Task("Digital Electronics","blhob",R.drawable.ic_baseline_edit_24,4);
//        Task t5 = new Task("Project architecture","rbna yostorha",R.drawable.ic_baseline_edit_24,5);
//        Task t6 = new Task("Engineering Economy",":)",R.drawable.ic_baseline_edit_24,6);
//        Task t7 = new Task("meeting","bring laptop",R.drawable.ic_baseline_edit_24,7);
//        Task t8 = new Task("OOP","good",R.drawable.ic_baseline_edit_24,8);*/
//        //   taskModelArrayList.addAll(Arrays.asList(t1,t2,t3,t4,t5,t6,t7,t8));
//        // Log.d(TAG,"onCreate: " + taskModelArrayList.toString());
//    }

    @Override
    public void OnItemClick(int position) {
        //this method is implements the interface -RecycleViewInterface- to handle user clicks on
        /*it sends out the current pressed item details to the ShowTaskDetails class by getting the position
        of the item pressed and by using the getters in the taskModel class*/
        Intent intent = new Intent(TodayActivity.this,ShowTaskDetails.class);

        intent.putExtra("TaskName",taskModelArrayList.get(position).getTaskName());
        intent.putExtra("TaskDetails",taskModelArrayList.get(position).getTaskDetails());
        intent.putExtra("Image",taskModelArrayList.get(position).getImage());
        intent.putExtra("done",taskModelArrayList.get(position).isTaskDone());
        intent.putExtra("taskId",taskModelArrayList.get(position).getTaskId());


        startActivity(intent);



    }

    @Override
    public void onItemLongClick(int position) {
        //this method also implements the interface -RecycleViewInterface- to handle long clicks
        //long press can remove items from the list
        //by getting the position of the item pressed.. item at that position(index) will be removed
        //then notify the adapter to update the list
        taskModelArrayList.remove(position);
        adapter.notifyItemRemoved(position);
    }

    //onCreateOptionsMenu, onOptionsItemSelected methods overrides methods in Activity class
    //they handle the dropdown menu which can be used to sort the task as needed

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sort_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_done:
                Collections.sort(taskModelArrayList, Task.taskModelComparatorDone);
                Toast.makeText(TodayActivity.this, "sorted by done", Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();
                return true;
            case R.id.menu_sort_name:
                Collections.sort(taskModelArrayList, Task.taskModelComparatorName);
                Toast.makeText(TodayActivity.this, "sorted by name", Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}