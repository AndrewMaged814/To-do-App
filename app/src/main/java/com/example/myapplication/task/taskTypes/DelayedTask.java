package com.example.myapplication.task.taskTypes;

import com.example.myapplication.task.SortTaskList;
import com.example.myapplication.task.Task;

import java.util.LinkedList;
import java.util.List;

public class DelayedTask {
    static List<Task>DelayedTasks=new LinkedList<Task>();
    public static void add(Task task){

        DelayedTasks.add(task);
        SortTaskList. sortByPriority(DelayedTasks);

    }
}
