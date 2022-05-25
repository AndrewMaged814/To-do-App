package com.example.myapplication.task.taskTypes;

import com.example.myapplication.task.SortTaskList;
import com.example.myapplication.task.Task;

import java.util.LinkedList;
import java.util.List;

public class UpComing {

    public static List<Task>UpComingTasks=new LinkedList<Task>();
    public static void add(Task task){

        UpComingTasks.add(task);
        SortTaskList. sortByPriority(UpComingTasks);

    }
}

