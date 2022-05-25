package com.example.myapplication.task.taskTypes;


import com.example.myapplication.task.SortTaskList;
import com.example.myapplication.task.Task;

import java.util.ArrayList;

public class Today {
   public static ArrayList<Task>TodTasks=new ArrayList<>();
   public static void add(Task task) {
                 TodTasks.add(task);
                 SortTaskList.sortByPriority(TodTasks);

   }


}
