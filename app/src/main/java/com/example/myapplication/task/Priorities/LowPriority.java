package com.example.myapplication.task.Priorities;


import com.example.myapplication.task.SortTaskList;
import com.example.myapplication.task.Task;

public  class LowPriority extends Task
{

    public LowPriority(String Name, String Category, String p, int day, int month, int year, String date){
        super(Name,Category,p,day,month,year,date);
        Priority="Low";
      SortTaskList.CompareTaskDate(this);
    }
    public LowPriority(LowPriority LowPriorityTask) {
        super(LowPriorityTask);
    }



}
