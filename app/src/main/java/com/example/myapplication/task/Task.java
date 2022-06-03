package com.example.myapplication.task;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.myapplication.R;
import com.example.myapplication.task.Activities.pomodoroTimer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.ArrayList;
import static com.example.myapplication.user.userTypes.User.eventsList;


public class Task {

    String TaskDetails;
    public String Name;
    public String Category;
    protected int day, month, year;
    protected int p;
    public String Priority;
    protected boolean done = false;
    public String date;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    protected static int numOfTasks;
    protected int taskId;
    public String notes;
    protected static final int image = R.drawable.ic_baseline_edit_24;
    pomodoroTimer pomodoro;

    //public static List<Task> eventsList = UpComing.UpComingTasks;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<Task> eventsForDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = date.format(formatter);
        ArrayList<Task> events = new ArrayList<>();


        for (Task event : eventsList) {
            if (event.getDate().equals(formattedString))
                events.add(event);
        }

        return events;
    }


    public Task(String Name, String Category, String Priority, int day, int month, int year, String date) {
        this.Name = Name;
        this.Category = Category;
        this.day = day;
        this.month = month;
        this.year = year;
        this.date = date;
        this.Priority = Priority;
        numOfTasks++;
        this.taskId = numOfTasks;

        this.TaskDetails = this.Name + "\n" + this.date + "\n" + this.Priority + "\n" + this.Category + "\n" + this.done + "\n" + this.taskId;
    }

    public Task(String Name, String date, int p,String Priority, String Category, String Done, String ID) {
        this.Name = Name;
        this.Category = Category;
        this.p = p;
        this.Priority = Priority;
        this.done = Boolean.parseBoolean(Done);
        this.date = date;
        this.taskId = numOfTasks++;
        this.TaskDetails = Name + date  + Priority + Category;
    }



    public Task(Task T) {
        this.Name = T.Name;
        this.Category = T.Category;
        this.p = T.p;
        this.day = T.day;
        this.month = T.month;
        this.year = T.year;
        this.date = T.date;
        this.Priority = T.Priority;
        this.TaskDetails = T.TaskDetails;
        this.taskId = T.taskId;
    }


    public String getTaskName() {
        return Name;
    }

    public String getTaskDetails() {
        return TaskDetails;
    }

    public void setDone() {
        done = true;
    }

    public boolean isTaskDone() {
        return done;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskNotes(String notes) {
        this.notes = notes;
    }

    public void setTaskDone(boolean taskDone) {
        this.done = taskDone;
    }

    public void setTaskDetails(String taskDetails) {
        TaskDetails = taskDetails;
    }

    public int getImage() {
        return image;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }


    public void DeleteTask(List<Task> t, String Name, Tasks_Store sh) {
        for (Task T : t) {
            if (T.Name.equals(Name)) {
                t.remove(T);
                sh.deleteTask(Name);
            }
        }
    }


    //to sort today's tasks based on weather the task is done or not
    //it will sort them by not done first then done(false,false,true,true,..)
    public static Comparator<Task> taskModelComparatorDone = new Comparator<Task>() {
        @Override
        public int compare(Task t1, Task t2) {
            return Boolean.compare(t1.isTaskDone(), t2.isTaskDone());
        }
    };
    //sort task names ascending
    public static Comparator<Task> taskModelComparatorName = new Comparator<Task>() {
        @Override
        public int compare(Task t1, Task t2) {
            return t1.getTaskName().compareToIgnoreCase(t2.getTaskName());
        }
    };


    public String toString() {

        return this.Name+"\n" + this.date +"\n"+ this.Priority+"\n"+this.p+"\n" + this.Category+"\n"+this.done+"\n"+this.taskId;
    }

}
