package com.example.myapplication.task.Priorities;

import com.example.myapplication.task.SortTaskList;
import com.example.myapplication.task.Task;

public  class ModeratePriority extends Task //implements Sort
{
   public ModeratePriority(String Name, String Category, String Priority, int day, int month, int year, String date){
      super(Name,Category,Priority,day,month,year,date);
      //Priority="Medium";
      this.p=2;
      SortTaskList.CompareTaskDate(this);
   }
   public ModeratePriority(HighPriority moderatePriorityTask) {
      super(moderatePriorityTask);
   }


}

