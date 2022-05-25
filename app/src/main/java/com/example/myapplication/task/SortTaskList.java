package com.example.myapplication.task;

import com.example.myapplication.task.taskTypes.DelayedTask;
import com.example.myapplication.task.taskTypes.Today;
import com.example.myapplication.task.taskTypes.UpComing;

import java.text.SimpleDateFormat;
import java.util.*;

public  interface SortTaskList {



 static public void sortByPriority(List <Task> l) {
        if (l.size()>1) {

            Collections.sort(l, new Comparator<Task>() {
                @Override

                public int compare(Task task, Task t1) {
                    if(task.p>t1.p)return -1 ;
                    else return 1;
                }
            });

        }

    }


   static public void CompareTaskDate(String s,Task task){

       String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
       String PrevDate = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault()).format(new Date(System.currentTimeMillis()-24*60*60*1000));

       if( s.contains(currentDate)){
           Today.add(task);

       }
       else if(s.contains(PrevDate)){
           DelayedTask.add(task);
       }

       else {
           UpComing.add(task);
       }


    }
    static void CompareTaskDate(Task task){

        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        String PrevDate = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault()).format(new Date(System.currentTimeMillis()-24*60*60*1000));

        if( task.date.contains(currentDate)){
            Today.add(task);

        }
        else if(task.date.contains(PrevDate)){
            DelayedTask.add(task);
        }

        else {
            UpComing.add(task);
        }


    }

}
