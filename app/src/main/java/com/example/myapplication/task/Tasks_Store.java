package com.example.myapplication.task;
import android.content.Context;
import android.content.SharedPreferences;
import com.example.myapplication.user.userTypes.NormalUser;
import com.example.myapplication.user.userTypes.User;

import java.util.*;


public class Tasks_Store {
    public static final String Tasks = "tasks";
    public static final String KEY_Task = "task";

    SharedPreferences tasksList;
    SharedPreferences.Editor editor;
    Context context;


    public Tasks_Store(Context context) {
        tasksList= context.getSharedPreferences(Tasks, 0);
        this.context = context;
        editor = tasksList.edit();


    }

    public void store(Task t) {

        Set<String> setT = new HashSet<String>(tasksList.getStringSet(KEY_Task, new HashSet<String>()));
        setT.addAll(Collections.singleton(t.toString()));
        editor.putStringSet(KEY_Task, setT);
        editor.commit();


    }

    public void getTask() {
        Set<String> setT = new HashSet<String>(tasksList.getStringSet(KEY_Task, new HashSet<String>()));
        // String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        for (String s:setT){
            String strings[]=s.split("\n",7);
            String Name=strings[0];
            String Date=strings[1];
            String Priority =strings[2];
            int P =Integer.parseInt(strings[3]);
            String Category= strings[4];
            String Done =strings[5];
            String ID =strings[6];
            Task task =new Task(Name,Date,P,Priority,Category,Done,ID);
            SortTaskList. CompareTaskDate(s,task);
            if(s.contains("true")){
                deleteTask(s);
            }

        }

    }

    public  void deleteTask(String name) {
        Set<String> setT = new HashSet<String>(tasksList.getStringSet(KEY_Task, new HashSet<String>()));
        for (Object s:setT.toArray()){
            String string=s.toString();
            if (string.contains(name)) {
                setT.remove(s);
                editor.commit();
                editor.putStringSet(KEY_Task, setT);
                editor.commit();
                break;
            }

        }
    }

    public void EditTask(String Name,Task tNew){
        Set<String> setT = new HashSet<String>(tasksList.getStringSet(KEY_Task, new HashSet<String>()));

        for (Object s:setT.toArray()){
            String string=s.toString();
            if (string.contains(Name)) {
                setT.remove(s);
                editor.commit();
                setT.addAll(Collections.singleton(tNew.toString()));
                editor.putStringSet(KEY_Task, setT);
                editor.commit();
                break;
            }

        }


    }
    public void clearAllTasks(){
        editor.clear();
        editor.apply();
    }

}