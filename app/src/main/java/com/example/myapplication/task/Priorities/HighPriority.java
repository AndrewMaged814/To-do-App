package com.example.myapplication.task.Priorities;


import com.example.myapplication.task.SortTaskList;
import com.example.myapplication.task.Task;

public  class HighPriority extends Task {

    public HighPriority(String Name, String Category, String p, int day, int month, int year, String date) {
        super(Name, Category, p, day, month, year, date);
        Priority = "High";
        SortTaskList.CompareTaskDate(this);
    }

    public HighPriority(HighPriority highPriorityTask) {
        super(highPriorityTask);
    }




}






