package com.example.myapplication.task.taskTypes;

import com.example.myapplication.task.SortTaskList;
import com.example.myapplication.task.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DelayedTask {
    public static ArrayList<Task>DelayedTasks=new ArrayList<>();
    public static void add(Task task){

        DelayedTasks.add(task);
        SortTaskList. sortByPriority(DelayedTasks);

    }
}
